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

    public AbstractBusiness(){
        logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * Lancement du traitement du business.
     */
    public abstract AbstractModel call(AbstractModel model);

    /**
     * Indique si le traitement est asynchrone ou non
     * @return true si le traitement est asynchrone
     */
    public abstract boolean isAsynchronous();

    public abstract AbstractModel convert(AbstractModel model);


}
