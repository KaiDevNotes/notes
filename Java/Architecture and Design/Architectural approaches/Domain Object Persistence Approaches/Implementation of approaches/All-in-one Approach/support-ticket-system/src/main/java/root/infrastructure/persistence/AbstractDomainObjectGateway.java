package root.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractDomainObjectGateway<D> 
{
    @Transactional
    public D save(final D domainObject)
    {
        return getRepository().saveAndFlush(domainObject);
    }
    
    public D findById(final UUID id)
    {
        return getRepository().findOne(id);
        
    }
    
    public List<D> findAll()
    {
        return getRepository().findAll();
    }
    
    @Transactional
    public void delete(final UUID id)
    {
        getRepository().delete(id);
        getRepository().flush();
    }
    
    protected abstract JpaRepository<D, UUID> getRepository();
}