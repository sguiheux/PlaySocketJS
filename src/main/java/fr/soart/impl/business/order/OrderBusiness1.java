package fr.soart.impl.business.order;

import fr.soart.engine.business.AbstractOrderBusiness;
import fr.soart.engine.business.EnumBusinessService;
import fr.soart.engine.model.AbstractModel;
import fr.soart.impl.converter.OrderBusiness1ToSimpleBusiness1;
import fr.soart.impl.model.OrderBusiness1Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Sample Order Business.
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

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractModel convert(int identifiant) {
        if(identifiant == EnumBusinessService.BUSINESS_1.id){
             return converterToSimpleBusiness.convert((OrderBusiness1Model) this.model);
        }
        return null;
    }


}
