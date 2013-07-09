package fr.soart.engine.db.dao;

import fr.soart.engine.db.StepCollection;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Accès à la collection savedBusiness
 */
@Component
public class StepCollectionDAO extends AbstractDAO{

    /**
     * Find StepCollection
     * @return
     */
    public List<StepCollection> find() {
        Query searchEtapeQuery = new Query(Criteria.where("rootId").is(this.getClass().getName()));
        searchEtapeQuery.with(new Sort(Sort.Direction.ASC, "stepOrder"));
        return mongoOperation.find(searchEtapeQuery, StepCollection.class);
    }
}
