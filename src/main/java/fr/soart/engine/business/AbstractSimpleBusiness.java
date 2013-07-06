package fr.soart.engine.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 *     Traitement metier simple.
 */
public abstract class AbstractSimpleBusiness extends AbstractBusiness{



    /**
     * Constructeur.
     */
    public AbstractSimpleBusiness(){
        super();
    }

    public abstract void call();
}
