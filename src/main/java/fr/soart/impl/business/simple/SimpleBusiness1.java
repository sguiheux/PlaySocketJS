package fr.soart.impl.business.simple;

import fr.soart.impl.annotation.Business;
import fr.soart.engine.business.AbstractSimpleBusiness;
import fr.soart.engine.model.AbstractModel;
import fr.soart.impl.model.OrderBusiness1Model;
import fr.soart.impl.model.SimpleBusiness1Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Sample Business 1
 */
@Business
@Component
@Scope(value = "prototype")
public class SimpleBusiness1 extends AbstractSimpleBusiness {

    @Override
    public AbstractModel call(AbstractModel model) {

        logger.info("Call SimpleBusiness1");
        SimpleBusiness1Model sbm = new SimpleBusiness1Model();
        sbm.setText("azerty");
        return sbm;
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

    public SimpleBusiness1Model convert(OrderBusiness1Model m){
        SimpleBusiness1Model model = new SimpleBusiness1Model();
        model.setText(m.getTata()+m.getToto());
        return model;
    }


}
