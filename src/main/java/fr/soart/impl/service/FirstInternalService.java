package fr.soart.impl.service;

import com.test.test.test_api.PortType;
import com.test.test.test_api.Requete;
import com.test.test.test_api.Response;
import fr.soart.engine.db.ServiceCollection;
import fr.soart.impl.business.order.OrderSoartService1;
import fr.soart.impl.converter.FirstServiceToOrderBusiness1Converter;
import fr.soart.impl.model.OrderBusiness1Model;
import org.springframework.data.mongodb.core.MongoOperations;
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
    OrderSoartService1 business1;
    @Resource
    FirstServiceToOrderBusiness1Converter converter;

    @Resource(name = "mongoTemplate")
    private MongoOperations mongoOperation;

    @Override
    public Response testOperation(@WebParam(partName = "parameters", name = "Requete", targetNamespace = "http://test.test.com/test_api/") Requete parameters) {


        OrderBusiness1Model l = (OrderBusiness1Model) business1.call(converter.convert(parameters));

        ServiceCollection rb = new ServiceCollection();
        rb.setSpringName("rb");
        mongoOperation.save(rb);

        ServiceCollection rb2 = new ServiceCollection();
        rb2.setSpringName("rb2");
        mongoOperation.save(rb2);

        Response r = new Response();
        r.setType2ResponseField1(l.getTata());
        r.setType2ResponseField2(l.getToto());
        return r;
    }
}
