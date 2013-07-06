package fr.soart.engine.business;

import fr.soart.engine.model.AbstractModel;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract class AbstractOrderBusiness extends AbstractBusiness {

    /**
     * Liste des étapes.
     */
    Map<EnumBusinessService, Boolean> mapEtape;

    /**
     * Context Spring.
     */
    @Resource
    private ApplicationContext context;

    /**
     * Constructeur du business
     */
    public AbstractOrderBusiness() {
        super();
    }

    /**
     * Convertit les données dans un format approprié
     * @param destination Identifiant du model de destination
     * @return
     */
    public abstract AbstractModel convert(int destination);


    @Override
    public void call(){
        recuperationEtape();
        process();
    }



    private void process() {
        for (Map.Entry<EnumBusinessService, Boolean> entry : mapEtape.entrySet()) {
            if (!entry.getValue().booleanValue()) {
                EnumBusinessService e = entry.getKey();
                AbstractBusiness a = (AbstractBusiness) context.getBean(e.name,e.c);
                if(e.isSynchronous){
                    a.init(convert(e.id));
                    a.call();
                    entry.setValue(true);
                }

            }
        }
    }

    private void recuperationEtape(){
        mapEtape = new HashMap<EnumBusinessService, Boolean>();
        mapEtape.put(EnumBusinessService.BUSINESS_1,false);
        mapEtape.put(EnumBusinessService.BUSINESS_1,false);
        mapEtape.put(EnumBusinessService.BUSINESS_1,false);
    }
}
