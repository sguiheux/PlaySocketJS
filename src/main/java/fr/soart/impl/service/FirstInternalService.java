package fr.soart.impl.service;

import com.test.test.test_api.PortType;
import com.test.test.test_api.Requete;
import com.test.test.test_api.Response;
import fr.soart.engine.db.RefBusiness;
import fr.soart.impl.converter.FirstServiceToOrderBusiness1Converter;
import fr.soart.impl.business.order.OrderBusiness1;
import fr.soart.impl.model.OrderBusiness1Model;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;


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

    @Resource(name = "mongoTemplate")
      private MongoOperations mongoOperation;
    
    @Override
    public Response testOperation(@WebParam(partName = "parameters", name = "Requete", targetNamespace = "http://test.test.com/test_api/") Requete parameters) {


        RefBusiness rb = new RefBusiness();
        rb.setSpringName("orderBusiness1");

        Query searchBusinessQuery = new Query(Criteria.where("springName").is("orderBusiness1"));
        RefBusiness savedBusiness = mongoOperation.findOne(searchBusinessQuery, RefBusiness.class);
        if(savedBusiness == null){
            mongoOperation.save(rb);
        }
        RefBusiness rb2 = new RefBusiness();
        rb2.setSpringName("simpleBusiness1");

        mongoOperation.save(rb2);


        List<RefBusiness> list =  mongoOperation.findAll(RefBusiness.class);
       // OrderBusiness1Model l = (OrderBusiness1Model) business1.call(converter.convert(parameters));

        Response r = new Response();
        r.setType2ResponseField1(list.size()+"");
        //r.setType2ResponseField1(l.getTata());
        //r.setType2ResponseField2(l.getToto());
        return r;
    }
}
