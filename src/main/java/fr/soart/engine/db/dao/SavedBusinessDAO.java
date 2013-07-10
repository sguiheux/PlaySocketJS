package fr.soart.engine.db.dao;

import fr.soart.engine.db.SavedService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Accès à la collection savedBusiness
 */
@Component
public class SavedBusinessDAO extends AbstractDAO {

    /**
     * Recupere l'etat de traitement a partir de l'id de correlation
     * @param idCorrelation Identifiant de correlation
     * @return Business
     */
    public SavedService findByIdCorrelation(String idCorrelation) {
        Query searchSavedBusinessQuery = new Query(Criteria.where("idCorrelation").is(idCorrelation));
        return mongoOperation.findOne(searchSavedBusinessQuery,SavedService.class);
    }
}
