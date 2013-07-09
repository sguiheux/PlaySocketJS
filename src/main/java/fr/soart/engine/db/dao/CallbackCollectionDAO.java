package fr.soart.engine.db.dao;

import fr.soart.engine.db.CallbackCollection;

import java.util.List;

/**
 * Accès à la collection des callbacks
 */
public class CallbackCollectionDAO extends AbstractDAO {

    /**
     * Recuperer toutes les connexions.
     * @return
     */
    public List<CallbackCollection> findAll(){
           return mongoOperation.findAll(CallbackCollection.class);
    }
}
