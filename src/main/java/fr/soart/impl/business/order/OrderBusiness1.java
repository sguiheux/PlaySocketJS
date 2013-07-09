package fr.soart.impl.business.order;

import fr.soart.engine.business.AbstractOrderBusiness;
import fr.soart.engine.model.AbstractModel;
import fr.soart.impl.converter.OrderBusiness1ToSimpleBusiness1;
import fr.soart.impl.model.OrderBusiness1Model;
import fr.soart.impl.model.SimpleBusiness1Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Sample StepOrder Business.
 */
@Component
@Scope(value = "prototype")
public class OrderBusiness1 extends AbstractOrderBusiness{

    public static final String COMPONENT_NAME = "orderBusiness1";

    @Resource
    private OrderBusiness1ToSimpleBusiness1 converterToSimpleBusiness;

    /**
     * Constructeur du business
     */
    public OrderBusiness1() {
        super();
    }

    @Override
    protected String toXml(AbstractModel model) {
        return null;
    }

    @Override
    public boolean isAsynchronous() {
        return false;
    }

    @Override
    public AbstractModel convert(AbstractModel model) {
        logger.error("Erreur on ne doit pas arriver ici");
        return null;
    }

    public OrderBusiness1Model convert(SimpleBusiness1Model m){
        OrderBusiness1Model o = new OrderBusiness1Model();
        o.setTata(m.getText());
        return o;
    }


}
