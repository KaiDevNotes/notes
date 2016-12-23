package kai.dev.springdata.dao;

import javax.annotation.Resource;
import kai.dev.springdata.dao.repository.LocationRepository;
import kai.dev.springdata.entity.Location;
import kai.dev.springdata.exception.EntityNotFound;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class LocationDAO extends AbstractDAO<Location> {
    
    @Resource
    private LocationRepository locationRepository; 
    
    @Transactional
    public Location getByName(String name) 
    {
        return locationRepository.findByName(name);
    }   
    
    @Transactional(rollbackFor = EntityNotFound.class)
    public Location getByIdWithContinentAndServers(Integer id) throws EntityNotFound
    {
        Location location = getById(id);
        if (location == null){
            throw new EntityNotFound();
        }
        Hibernate.initialize(location.getContinent());
        Hibernate.initialize(location.getServers());
        return location;
    }
    
    //--------------------------------------------------------
    
    @Override
    protected LocationRepository getEntityRepository()
    {
        return locationRepository;
    }
}
