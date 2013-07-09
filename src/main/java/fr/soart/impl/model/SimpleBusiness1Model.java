package fr.soart.impl.model;

import fr.soart.engine.model.AbstractModel;

/**
 * Model de donnée de SimpleSoartService1.
 */
public class SimpleBusiness1Model extends AbstractModel {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getIdCorrelation() {
        // Null car service synchrone
        return null;
    }
}
