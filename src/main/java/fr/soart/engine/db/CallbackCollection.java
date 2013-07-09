package fr.soart.engine.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represente les callbacks persisté
 */
@Document(collection = "callback")
public class CallbackCollection {

    @Id
    private String id;

    private String message;

    private String orderBusinessId;

    private String simpleBusinessId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderBusinessId() {
        return orderBusinessId;
    }

    public void setOrderBusinessId(String orderBusinessId) {
        this.orderBusinessId = orderBusinessId;
    }

    public String getSimpleBusinessId() {
        return simpleBusinessId;
    }

    public void setSimpleBusinessId(String simpleBusinessId) {
        this.simpleBusinessId = simpleBusinessId;
    }
}


