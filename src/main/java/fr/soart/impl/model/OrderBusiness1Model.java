package fr.soart.impl.model;

import fr.soart.engine.model.AbstractModel;

/**
 * Modele de donnée pour OrderBusiness1Model
 */
public class OrderBusiness1Model extends AbstractModel {

    private String toto;

    private String tata;

    public OrderBusiness1Model(){

    }

    public String getToto() {
        return toto;
    }

    public void setToto(String toto) {
        this.toto = toto;
    }

    public String getTata() {
        return tata;
    }

    public void setTata(String tata) {
        this.tata = tata;
    }

    @Override
    public String getIdCorrelation() {
        // Non applicable car OrderBusiness
        return null;
    }
}
