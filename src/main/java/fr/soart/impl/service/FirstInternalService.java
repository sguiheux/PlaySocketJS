package fr.soart.impl.service;

import com.test.test.test_api.PortType;
import com.test.test.test_api.Requete;
import com.test.test.test_api.Response;
import fr.soart.impl.converter.FirstServiceToOrderBusiness1Converter;
import fr.soart.impl.business.order.OrderBusiness1;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 *
 */
@Service
@WebService(endpointInterface = "com.test.test.test_api.PortType")
public class FirstInternalService implements PortType {

    @Resource
    OrderBusiness1 business1;

    @Resource
    FirstServiceToOrderBusiness1Converter converter;
    
    
    @Override
    public Response testOperation(@WebParam(partName = "parameters", name = "Requete", targetNamespace = "http://test.test.com/test_api/") Requete parameters) {

        business1.init(converter.convert(parameters));
        business1.call();

        Response r = new Response();
        r.setType2ResponseField1("R");
        r.setType2ResponseField2("R2");
        r.setType2ResponseField3("R3");
        return r;
    }
}
