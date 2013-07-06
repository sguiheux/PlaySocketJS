package fr.soart.impl.converter;

import fr.soart.impl.model.OrderBusiness1Model;
import fr.soart.impl.model.SimpleBusiness1Model;
import org.springframework.stereotype.Component;

/**
 * Converter de orderBusiness vers Simple Business
 */
@Component
public class OrderBusiness1ToSimpleBusiness1 {

    /**
     * COnverter.
     * @param b
     * @return
     */
    public SimpleBusiness1Model convert(OrderBusiness1Model b){
        SimpleBusiness1Model sbm = new SimpleBusiness1Model();
        sbm.setText(b.getTata()+"-"+b.getToto());
        return sbm;
    }
}
