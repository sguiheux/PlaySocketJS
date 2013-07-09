package fr.soart.engine.business;

import fr.soart.engine.db.SavedBusiness;
import fr.soart.engine.db.StepCollection;
import fr.soart.engine.db.dao.SavedBusinessDAO;
import fr.soart.engine.db.dao.StepCollectionDAO;
import fr.soart.engine.model.AbstractModel;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Classe abstraite pour la gestion des services d'ordonnancement
 */
public abstract class AbstractOrderBusiness extends AbstractBusiness {

    /**
     * Liste des étapes à ordonnancer.
     */
    List<StepCollection> listEtape;
    /**
     * Context Spring.
     */
    @Resource
    private ApplicationContext context;
    /**
     * Accès aux business sauvegardé
     */
    @Resource
    private SavedBusinessDAO savedBusinessDAO;
    /**
     * Accès a la collection des etapes.
     */
    @Resource
    private StepCollectionDAO stepCollectionDAO;

    /**
     * Constructeur du business
     */
    public AbstractOrderBusiness() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractModel call(AbstractModel model) {
        boolean bool = process(model, 0);
        model.setTermine(bool);
        return model;

    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception
     */
    @PostConstruct
    public void init() throws Exception {
        this.listEtape = stepCollectionDAO.find();
    }

    /**
     * Lance le traitement.
     *
     * @param model     Modele de données
     * @param startStep etape de depart
     */
    private boolean process(AbstractModel model, int startStep) {
        // Y a t il des étapes
        if (listEtape != null) {
            // parcours des étapes
            for (int i = startStep; i < listEtape.size(); i++) {
                // recup de l'etape courante et du bean spring associé
                StepCollection steps = listEtape.get(i);
                AbstractBusiness a = (AbstractBusiness) context.getBean(steps.getChildId());

                // Creation de la requete pour appeler ce traitement
                AbstractModel modelDestination = a.convertToMyModel(model);

                // Lancement du traitement
                AbstractModel modelResult = a.call(modelDestination);

                // Gestion du retour
                if (modelResult != null) {
                    updateMyModel(model, modelResult);
                }

                // Si etape asynchrone, sauvegarde en base
                if (a.isAsynchronous()) {
                    // Persistence en base
                    SavedBusiness sb = new SavedBusiness();
                    sb.setBusinessName(this.getClass().getName());

                    // TODO Gestion id correlation
                    sb.setIdCorrelation("");
                    sb.setStepNumber(i);

                    sb.setModel(toXml(model));
                    savedBusinessDAO.save(sb);
                    return false;
                }
            }
        } else {
            this.logger.error("Aucune etape d'ordonnancement");
        }
        return true;
    }

    /**
     * Recover des callback
     *
     * @param message          Message du callback
     * @param simpleBusinessId
     */
    public void recover(String message, String simpleBusinessId, String id) {
        // TODO Gestion id correlation
        SavedBusiness sb = savedBusinessDAO.findByIdCorrelation("");

        String model = sb.getModel();
        AbstractModel soartModel = toModel(model);

        AbstractBusiness simpleBusiness = (AbstractBusiness) context.getBean(simpleBusinessId);
        AbstractModel simpleBusinessModel = simpleBusiness.toModel(message);

        updateMyModel(soartModel, simpleBusinessModel);

        int startStep = sb.getStepNumber();


        process(soartModel, startStep);

    }

    protected abstract String toXml(AbstractModel model);

    /**
     * Met à jour le modele du business à partir d'un autre model
     * @param myModel Modele à metter à jour
     * @param model Modele distant
     */
    public abstract void updateMyModel(AbstractModel myModel,AbstractModel model);


}
