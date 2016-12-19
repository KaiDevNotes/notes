package DAO;

import entity.Continent;
import javax.persistence.TypedQuery;


public class ContinentDAO extends AbstractDAO<Continent> {
    
    public ContinentDAO(){
        super(Continent.class);
    }
    
    public Continent getByName(String name)
    {
        TypedQuery<Continent> query = entityManager.createQuery(
            "SELECT c FROM Continent AS c WHERE c.name = ?1", 
            Continent.class
        );
        query.setParameter(1, name);
        Continent continent = query.getSingleResult();        
        return continent;
    }
}

