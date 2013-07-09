package fr.soart.engine.business;

import fr.soart.engine.db.SavedBusiness;
import fr.soart.engine.db.StepCollection;
import fr.soart.engine.model.AbstractModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort.Direction;

import javax.annotation.Resource;
import java.util.List;

/**
 * Classe abstraite pour la gestion des services d'ordonnancement
 */
public abstract class AbstractOrderBusiness extends AbstractBusiness implements InitializingBean {

    /**
     * Liste des étapes.
     */
    List<StepCollection> listEtape;

    /**
     * Context Spring.
     */
    @Resource
    private ApplicationContext context;

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
        boolean bool = process(model);
        model.setTermine(bool);
        return model;

    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        recuperationEtape();
    }

    /**
     * Lance le traitement.
     */
    private boolean process(AbstractModel model) {
        if (listEtape != null) {
            for (StepCollection steps : listEtape) {
                AbstractBusiness a = (AbstractBusiness) context.getBean(steps.getChildId());

                AbstractModel modelDestination = a.convert(model);
                AbstractModel modelResult = a.call(modelDestination);
                if(modelResult != null){
                    model = convert(modelResult);
                }
                if (a.isAsynchronous()) {
                    // Persistence en base
                    SavedBusiness sb = new SavedBusiness();
                    sb.setBusinessName(this.getClass().getName());

                    // TODO
                    sb.setIdCorrelation("");

                    sb.setModel(toXml(model));
                    mongoOperation.save(sb);
                    return false;
                }
            }
        } else {
            this.logger.error("Aucune etape d'ordonnancement");
        }
        return true;
    }

    protected abstract String toXml(AbstractModel model);

    /**
     * Recuperation en base des etapes de traitement
     */
    private void recuperationEtape() {
        Query searchEtapeQuery = new Query(Criteria.where("rootId").is(this.getClass().getName()));
        searchEtapeQuery.with(new Sort(Direction.ASC, "stepOrder"));
        this.listEtape = mongoOperation.find(searchEtapeQuery,StepCollection.class);
    }
}
