package DAO;

import entity.Location;
import javax.persistence.TypedQuery;


public class LocationDAO extends AbstractDAO<Location> {
    
    public LocationDAO(){
        super(Location.class);
    }
    
    public Location getByName(String name)
    {
        TypedQuery<Location> query = entityManager.createQuery(
            "SELECT l FROM Location AS l WHERE l.name = ?1", 
            Location.class
        );
        query.setParameter(1, name);
        Location location = query.getSingleResult();        
        return location;
    }
}

