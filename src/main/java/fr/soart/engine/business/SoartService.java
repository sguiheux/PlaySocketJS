package fr.soart.engine.business;

import fr.soart.engine.model.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represente un Service.
 */
public abstract class SoartService {

    /**
     * Logger.
     */
    protected Logger logger;

    /**
     * Constructeur.
     * Initialisation du logger
     */
    public SoartService() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * Lancement du traitement du business.
     * @param model Modele de données manipulé par le business
     * @return Modele de données mis à jour par l'ensemble des sous appels
     */
    public abstract AbstractModel call(AbstractModel model);

    /**
     * Indique si le traitement est asynchrone ou non
     *
     * @return true si le traitement est asynchrone
     */
    public abstract boolean isAsynchronous();

    /**
     * Converti le modele passé en parametre dans le modele de sortie
     * @param model Modele à convertir
     * @return Modele cible
     */
    public abstract AbstractModel convertToMyModel(AbstractModel model);

   /**
     * Conversion du model (xml) vers le model java
     * @param model à convertir
     * @return  Model de destination
     */
    public abstract AbstractModel toModel(String model);


}
