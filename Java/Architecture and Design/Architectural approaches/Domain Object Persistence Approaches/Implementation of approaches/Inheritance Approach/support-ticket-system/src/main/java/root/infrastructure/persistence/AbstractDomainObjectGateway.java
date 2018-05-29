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
    
    public D findById(String domainObjectId)
    {
        return (D) getRepository().findOne(UUID.fromString(domainObjectId));
    }
    
    public List<D> findAll()
    {
        return getRepository().findAll().stream()
            .map(domainObjectDbEntry -> (D) domainObjectDbEntry)
            .collect(Collectors.toList());
    }
    
    @Transactional
    public void delete(String domainObjectId)
    {
        getRepository().delete(UUID.fromString(domainObjectId));
        getRepository().flush();
    }
    
    protected abstract JpaRepository<E, UUID> getRepository();
}