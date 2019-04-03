package DAO;

import entity.Server;
import javax.persistence.TypedQuery;


public class ServerDAO extends AbstractDAO<Server> {
    
    public ServerDAO(){
        super(Server.class);
    }
    
    public Server getByName(String name)
    {
        TypedQuery<Server> query = entityManager.createQuery(
            "SELECT s FROM Server AS s WHERE s.name = ?1", 
            Server.class
        );
        query.setParameter(1, name);
        Server server = query.getSingleResult();        
        return server;
    }
}
