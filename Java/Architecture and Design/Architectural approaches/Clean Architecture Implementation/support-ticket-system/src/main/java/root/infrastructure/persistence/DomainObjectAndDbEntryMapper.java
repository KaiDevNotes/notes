package root.infrastructure.persistence;

public interface DomainObjectAndDbEntryMapper<D, E> 
{
    E mapToDbEntry(D domainObject);
    D mapToDomainObject(E dbEntry);
}
