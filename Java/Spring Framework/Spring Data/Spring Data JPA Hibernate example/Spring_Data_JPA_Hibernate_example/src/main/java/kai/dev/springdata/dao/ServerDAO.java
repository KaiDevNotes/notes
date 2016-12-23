package kai.dev.springdata.dao;

import java.util.List;
import javax.annotation.Resource;
import kai.dev.springdata.dao.repository.ServerRepository;
import kai.dev.springdata.entity.Location;
import kai.dev.springdata.entity.Server;
import kai.dev.springdata.exception.EntityNotFound;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ServerDAO extends AbstractDAO<Server> {
    
    @Resource
    private ServerRepository serverRepository; 
    
    @Transactional
    public Server getByName(String name) 
    {
        return serverRepository.findByName(name);
    }
    
    @Transactional
    public List<Server> getAllByRam(Integer ram) 
    {
        return serverRepository.findAllByRam(ram);
    }
    
    @Transactional
    public Server getByNameAndRam(String name, Integer ram) 
    {
        return serverRepository.findByNameAndRam(name, ram);
    }   
    
    @Transactional(rollbackFor = EntityNotFound.class)
    public Server getByIdWithLocationAndContinent(Integer id) throws EntityNotFound
    {
        Server server = getById(id);
        if (server == null){
            throw new EntityNotFound();
        }
        Hibernate.initialize(server.getLocation());
        Location location = server.getLocation();
        Hibernate.initialize(location.getContinent());
        return server;
    }
    
    //--------------------------------------------------------
    
    @Override
    protected ServerRepository getEntityRepository()
    {
        return serverRepository;
    }
}
