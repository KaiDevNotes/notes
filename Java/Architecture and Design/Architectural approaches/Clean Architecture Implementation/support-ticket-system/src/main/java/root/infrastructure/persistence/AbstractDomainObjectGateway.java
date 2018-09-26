package root.infrastructure.persistence;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import root.domain.DomainObject;

public abstract class AbstractDomainObjectGateway<D extends DomainObject, E extends DbEntry>
{
    @Transactional
    public D save(final D domainObject)
    {
        final E dbEntry = getRepository().saveAndFlush(getMapper().mapToDbEntry(domainObject));
        return getMapper().mapToDomainObject(dbEntry);
    }
    
    public D findById(final String domainObjectId)
    {
        final E dbEntry = getRepository().findOne(UUID.fromString(domainObjectId));
        return getMapper().mapToDomainObject(dbEntry);
    }
    
    public List<D> findAll()
    {
        return getRepository().findAll().stream()
            .map(dbEntry -> getMapper().mapToDomainObject(dbEntry))
            .collect(Collectors.toList());
    }
    
    @Transactional
    public void delete(final String domainObjectId)
    {
        getRepository().delete(UUID.fromString(domainObjectId));
        getRepository().flush();
    }
    
    protected abstract JpaRepository<E, UUID> getRepository();
    
    protected abstract DomainObjectAndDbEntryMapper<D, E> getMapper();
}