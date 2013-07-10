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

    /** Nom du bean d'ou vient le callback. */
    private String simpleSoartServiceId;

    /**
     * Getter de l'id unique
     * @return Identifiant unique
     */
    public String getId() {
        return id;
    }

    /**
     * Getter du message de callback
     * @return  Message de callback
     */
    public String getMessage() {
        return message;
    }

    /**
     * Getter de l'id du service à l'origine du callback
     * @return Identifiant du service d'origine du callback
     */
    public String getSimpleSoartServiceId() {
        return simpleSoartServiceId;
    }
}


