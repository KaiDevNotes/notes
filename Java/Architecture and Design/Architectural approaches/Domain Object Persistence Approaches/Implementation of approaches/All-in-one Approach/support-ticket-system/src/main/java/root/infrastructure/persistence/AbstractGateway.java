package root.infrastructure.persistence;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractGateway<D> 
{
    @Transactional
    public void save(D domainObject)
    {
        getRepository().saveAndFlush(domainObject);
    }
    
    public D findById(UUID id)
    {
        return getRepository().findOne(id);
        
    }
    
    public List<D> findAll()
    {
        return getRepository().findAll();
    }
    
    @Transactional
    public void delete(UUID id)
    {
        getRepository().delete(id);
        getRepository().flush();
    }
    
    protected abstract JpaRepository<D, UUID> getRepository();
}