package fr.soart.engine.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Collection des business sauvegarder en base en attente d'un retour asynchrone.
 */
@Document(collection = "business")
public class SavedBusiness {

    @Id
    private String id;
    private String idCorrelation;
    private String businessName;
    private String model;
    private int stepNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCorrelation() {
        return idCorrelation;
    }

    public void setIdCorrelation(String idCorrelation) {
        this.idCorrelation = idCorrelation;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
