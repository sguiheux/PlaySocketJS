package fr.soart.engine.model;

/**
 * Modele de donnee
 */
public class AbstractModel {

    /** Boolean indiquant si le traitemetn est fini. */
    private boolean termine;

    public void setTermine(boolean bool){
        this.termine = bool;
    }

    protected boolean isTermine(){
        return this.termine;
    }
}

