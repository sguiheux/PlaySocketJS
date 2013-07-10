package fr.soart.engine.recovery;

import fr.soart.engine.business.OrderSoartService;
import fr.soart.engine.business.SimpleSoartService;
import fr.soart.engine.business.SoartService;
import fr.soart.engine.db.CallbackCollection;
import fr.soart.engine.db.SavedService;
import fr.soart.engine.db.dao.CallbackCollectionDAO;
import fr.soart.engine.db.dao.SavedBusinessDAO;
import fr.soart.engine.model.AbstractModel;
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

    /**
     * Accès aux business sauvegardé
     */
    @Resource
    private SavedBusinessDAO savedBusinessDAO;

    public void process() {
        // Recuperation des callbacks
        // TODO recup par paquet- order
        List<CallbackCollection> callbacks = callbackCollectionDAO.findAll();
        for (CallbackCollection callback : callbacks) {

            // TODO : Mode cluster -  Choix du noeud le plus adapter

            // Recuperation du message de callback
            String message = callback.getMessage();
            // Recuperation de service à l'origine du callback
            SimpleSoartService simpleSoartService = (SimpleSoartService) context.getBean(callback.getSimpleSoartServiceId());
            AbstractModel simpleBusinessModel = simpleSoartService.toModel(message);

            // Recuperation du traitement sauvegardé
            SavedService sb = savedBusinessDAO.findByIdCorrelation(simpleBusinessModel.getIdCorrelation());

            // Recuperation du service à reveiller.
            OrderSoartService orderSoartService = context.getBean(sb.getOrderSoartService(), OrderSoartService.class);

            // Recuperation du contexte
            String model = sb.getModel();
            AbstractModel soartModel = orderSoartService.toModel(model);

            // Recover
            orderSoartService.recover(soartModel,simpleBusinessModel,sb.getStepNumber());

        }
    }
}
