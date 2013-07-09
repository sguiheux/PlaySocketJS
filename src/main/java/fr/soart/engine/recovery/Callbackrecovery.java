package fr.soart.engine.recovery;

import fr.soart.engine.business.OrderSoartService;
import fr.soart.engine.db.CallbackCollection;
import fr.soart.engine.db.dao.CallbackCollectionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Recovery automatique des callbacks asynchrone.
 */
@Component
public class CallbackRecovery {

    @Resource
    private ApplicationContext context;
    @Resource
    private CallbackCollectionDAO callbackCollectionDAO;

    public void process() {
        // Recuperation des callbacks
        // TODO recup par paquet- order
        List<CallbackCollection> callbacks = callbackCollectionDAO.findAll();
        for (CallbackCollection callback : callbacks) {

            // TODO : Mode cluster -  Choix du noeud le plus adapter
            String message = callback.getMessage();
            OrderSoartService business = context.getBean(callback.getOrderBusinessId(), OrderSoartService.class);
            business.recover(message, callback.getSimpleBusinessId(), callback.getId());
        }
    }
}
