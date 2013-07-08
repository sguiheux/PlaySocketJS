package fr.soart.engine.business;

import fr.soart.engine.model.AbstractModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * Classe abstraite pour la gestion des services d'ordonnancement
 */
public abstract class AbstractOrderBusiness extends AbstractBusiness implements InitializingBean {

    /**
     * Liste des étapes.
     */
    List<String> listEtape;

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
            for (String etape : listEtape) {
                AbstractBusiness a = (AbstractBusiness) context.getBean(etape);

                AbstractModel modelDestination = a.convert(model);
                AbstractModel modelResult = a.call(modelDestination);
                model = convert(modelResult);
                if (a.isAsynchronous()) {
                    // Persistence en base


                    return false;
                }
            }
        } else {
            this.logger.error("Aucune etape d'ordonnancement");
        }
        return true;
    }

    /**
     * Recuperation en base des etapes de traitement
     */
    private void recuperationEtape() {

    }
}
