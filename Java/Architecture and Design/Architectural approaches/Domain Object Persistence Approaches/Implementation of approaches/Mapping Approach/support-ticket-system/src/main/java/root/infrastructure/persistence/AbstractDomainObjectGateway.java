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
        E dbEntry = getRepository().saveAndFlush(getMapper().mapToDbEntry(domainObject));
        return getMapper().mapToDomainObject(dbEntry);
    }
    
    public D findById(String domainObjectId)
    {
        E dbEntry = getRepository().findOne(UUID.fromString(domainObjectId));
        return getMapper().mapToDomainObject(dbEntry);
    }
    
    public List<D> findAll()
    {
        return getRepository().findAll().stream()
            .map(dbEntry -> getMapper().mapToDomainObject(dbEntry))
            .collect(Collectors.toList());
    }
    
    @Transactional
    public void delete(String domainObjectId)
    {
        getRepository().delete(UUID.fromString(domainObjectId));
        getRepository().flush();
    }
    
    protected abstract JpaRepository<E, UUID> getRepository();
    
    protected abstract DomainObjectAndDbEntryMapper<D, E> getMapper();
}