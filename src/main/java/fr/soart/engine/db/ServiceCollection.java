package fr.soart.engine.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represente la liste des services déployés
 */
@Document(collection = "services")
public class ServiceCollection {

    /** identifiant unique. */
    @Id
    private String id;

    /** Nom du composant spring. */
    private String springName;

    /**
     * Setter du compsoant spring.
     * @param springName Nom du composant spring
     */
    public void setSpringName(String springName) {
        this.springName = springName;
    }

    /**
     * To String
     * @return  Chaine de caractere representant l'objet
     */
    @Override
    public String toString() {
        return "service [id=" + id + ", name=" + springName + "]";
    }

}
