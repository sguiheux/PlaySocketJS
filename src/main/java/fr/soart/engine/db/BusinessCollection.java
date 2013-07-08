package fr.soart.engine.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "business")
public class BusinessCollection {

    @Id
    private String id;

    private String springName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpringName() {
        return springName;
    }

    public void setSpringName(String springName) {
        this.springName = springName;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + springName + "]";
    }

}
