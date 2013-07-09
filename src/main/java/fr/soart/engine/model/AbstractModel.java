package fr.soart.engine.model;

/**
 * Modele de donnee
 */
public abstract class AbstractModel {

    /** Boolean indiquant si le traitemetn est fini. */
    private boolean termine;

    /**
     * Setter du flag terminé
     * @param bool  booleen
     */
    public void setTermine(boolean bool){
        this.termine = bool;
    }

    /**
     * Indique si le traitement est terminé.
     * @return
     */
    protected boolean isTermine(){
        return this.termine;
    }

    /**
     * Retourne l'identifiant de correlation.
     * Utile uniquement pour les communications asynchrone
     * @return Identifiant de correlation
     */
    abstract public String getIdCorrelation();
}

