package ssp.common.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import ssp.common.domain.Aggregate;
import ssp.common.domain.Identity;

public abstract class AbstractAggregateJpaRepository<A extends Aggregate<ID>, ID extends Identity, E> 
{
    @Transactional
    public A save(A aggregate)
    {
        E dbEntry = getDbEntryRepository().saveAndFlush(getMapper().mapToDbEntry(aggregate));
        return getMapper().mapToAggregate(dbEntry);
    }
    
    public Optional<A> findById(ID id)
    {
        UUID dbEntryId = UUID.fromString(id.getValue());
        E dbEntry = getDbEntryRepository().findById(dbEntryId).orElseGet(() -> null);
        return Optional.ofNullable(getMapper().mapToAggregate(dbEntry));        
    }
    
    public List<A> findAll()
    {
        return getDbEntryRepository().findAll()
            .stream()
            .map(dbEntry -> getMapper().mapToAggregate(dbEntry))
            .collect(Collectors.toList());
    }
    
    @Transactional
    public void delete(A aggregate)
    {
        UUID dbEntryId = UUID.fromString(aggregate.getId().getValue());
        getDbEntryRepository().deleteById(dbEntryId);
    }
    
    protected abstract JpaRepository<E, UUID> getDbEntryRepository();
    
    protected abstract AggregateAndDbEntryMapper<A, ID, E> getMapper();
}
