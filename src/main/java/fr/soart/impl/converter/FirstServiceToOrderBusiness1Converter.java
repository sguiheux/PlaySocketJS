package fr.soart.impl.converter;

import com.test.test.test_api.Requete;
import fr.soart.impl.model.OrderBusiness1Model;
import org.springframework.stereotype.Component;

/**
 * Converter du service vers le business.
 */
@Component
public class FirstServiceToOrderBusiness1Converter {

    /**
     * Methode de conversion
     *
     * @param req
     * @return
     */
    public OrderBusiness1Model convert(Requete req) {
        OrderBusiness1Model m = new OrderBusiness1Model();
        m.setTata(req.getType1().getType1Field1());
        m.setToto(req.getType2().getType2Field1());
        return m;
    }
}
