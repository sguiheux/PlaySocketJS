package fr.soart.engine.db.dao;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;

/**
 * Traitement commun aux Daos
 */
public class AbstractDAO {

    /** Operation Mongo. */
    @Resource(name = "mongoTemplate")
    protected MongoOperations mongoOperation;

    /**
     * Sauvegarde en base
     * @param obj Objet a sauver
     */
    public void save(Object obj){
        mongoOperation.save(obj);
    }
}
