package fr.soart.engine.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Collection des services sauvegarder en base en attente d'un retour asynchrone.
 */
@Document(collection = "business")
public class SavedService {

    /** Identifiant unique. */
    @Id
    private String id;

    /** Identifiant de correlation pour le retour asynchrone. */
    private String idCorrelation;

    /** Identifiant du service à reveiller. */
    private String orderSoartService;

    /** Contexte du traitement déja realisé. */
    private String model;

    /** Numero du step auquel le traitement s'est arreté. */
    private int stepNumber;

    /** Setter de l'idenfiant de correlation. */
    public void setIdCorrelation(String idCorrelation) {
        this.idCorrelation = idCorrelation;
    }

    /** Getter du contexte. */
    public String getModel() {
        return model;
    }

    /** Setter du contexte. */
    public void setModel(String model) {
        this.model = model;
    }

    /** Numero d'etape en cours. */
    public int getStepNumber() {
        return stepNumber;
    }

    /** Setter du numero d'etape en cours. */
    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    /** Setter du service à reveiller. */
    public void setOrderSoartService(String orderSoartService) {
        this.orderSoartService = orderSoartService;
    }

    /** Getter du service à reveiller; */
    public String getOrderSoartService() {
        return orderSoartService;
    }
}
