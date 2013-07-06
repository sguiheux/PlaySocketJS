package fr.soart.engine.business;

import fr.soart.engine.model.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Represente un Service business.
 */
public abstract class AbstractBusiness {

    /** Logger. */
    protected Logger logger;

    /**
     * Model de la classe business.
     */
    protected AbstractModel model;

    public AbstractBusiness(){
        logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * Initialisation du composant.
     * @param model Model du composant
     */
    public void init(AbstractModel model){
        this.model = model;
    }

    /**
     * Lancement du traitement du business.
     */
    public abstract void call();


}
