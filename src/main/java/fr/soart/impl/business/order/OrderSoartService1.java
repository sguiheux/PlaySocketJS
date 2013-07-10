package fr.soart.impl.business.order;

import fr.soart.engine.business.OrderSoartService;
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
public class OrderSoartService1 extends OrderSoartService {

    public static final String COMPONENT_NAME = "orderBusiness1";

    @Resource
    private OrderBusiness1ToSimpleBusiness1 converterToSimpleBusiness;

    /**
     * Constructeur du business
     */
    public OrderSoartService1() {
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
    public AbstractModel convertToMyModel(AbstractModel model) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateMyModel(AbstractModel myModel, AbstractModel model) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public OrderBusiness1Model convert(SimpleBusiness1Model m){
        OrderBusiness1Model o = new OrderBusiness1Model();
        o.setTata(m.getText());
        return o;
    }

    @Override
    public AbstractModel toModel(String model) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
