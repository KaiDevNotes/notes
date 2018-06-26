package root.infrastructure.persistence;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractDomainObjectGateway<D, E> 
{
    @Transactional
    public D save(D domainObject)
    {
        return (D) getRepository().saveAndFlush((E) domainObject);
    }
    
    public D findById(UUID domainObjectId)
    {
        return (D) getRepository().findOne(domainObjectId);
    }
    
    public List<D> findAll()
    {
        return getRepository().findAll().stream()
            .map(domainObjectDbEntry -> (D) domainObjectDbEntry)
            .collect(Collectors.toList());
    }
    
    @Transactional
    public void delete(UUID domainObjectId)
    {
        getRepository().delete(domainObjectId);
        getRepository().flush();
    }
    
    protected abstract JpaRepository<E, UUID> getRepository();
}