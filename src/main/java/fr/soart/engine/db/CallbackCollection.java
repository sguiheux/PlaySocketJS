package fr.soart.engine.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represente les callbacks persisté
 */
@Document(collection = "callbacks")
public class CallbackCollection {

    /** identifiant unique. */
    @Id
    private String id;

    /** Message de callback. */
    private String message;

    /** Nom du bean à reveiller. */
    private String orderBusinessId;

    /** Nom du bean d'ou vient le callback. */
    private String simpleBusinessId;

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getOrderBusinessId() {
        return orderBusinessId;
    }

    public String getSimpleBusinessId() {
        return simpleBusinessId;
    }
}


