package kai.dev.springdata.dao;

import java.util.List;
import javax.annotation.Resource;
import kai.dev.springdata.dao.repository.ContinentRepository;
import kai.dev.springdata.entity.Continent;
import kai.dev.springdata.entity.Location;
import kai.dev.springdata.exception.EntityNotFound;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ContinentDAO extends AbstractDAO<Continent> {
    
    @Resource
    private ContinentRepository continentRepository; 
    
    @Transactional
    public Continent getByName(String name) 
    {
        return continentRepository.findByName(name);
    }  
    
    @Transactional(rollbackFor = EntityNotFound.class)
    public Continent getByIdWithLocations(Integer id) throws EntityNotFound
    {
        Continent continent = continentRepository.findOne(id);
        if (continent == null){
            throw new EntityNotFound();
        }
        Hibernate.initialize(continent.getLocations());
        return continent;
    }
    
    @Transactional(rollbackFor = EntityNotFound.class)
    public Continent getByIdWithLocationsAndServers(Integer id) throws EntityNotFound
    {
        Continent continent = continentRepository.findOne(id);
        if (continent == null){
            throw new EntityNotFound();
        }
        Hibernate.initialize(continent.getLocations());
        List<Location> locations = continent.getLocations();
        for (Location location : locations)
        {
            Hibernate.initialize(location.getServers());
        }
        return continent;
    }
    
    //--------------------------------------------------------
    
    @Override
    protected ContinentRepository getEntityRepository()
    {
        return continentRepository;
    }
}
