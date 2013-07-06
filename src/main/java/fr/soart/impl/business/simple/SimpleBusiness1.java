package fr.soart.impl.business.simple;

import fr.soart.engine.business.AbstractSimpleBusiness;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Sample Business 1
 */
@Component
@Scope(value = "prototype")
public class SimpleBusiness1 extends AbstractSimpleBusiness {

    public static final String COMPONENT_NAME = "simpleBusiness1";

    @Override
    public void call() {
        logger.info("Call SimpleBusiness1");
    }
}
