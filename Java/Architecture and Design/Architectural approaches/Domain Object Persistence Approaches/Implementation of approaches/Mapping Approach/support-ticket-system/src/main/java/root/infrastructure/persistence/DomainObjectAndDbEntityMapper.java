package root.infrastructure.persistence;

public interface DomainObjectAndDbEntityMapper<D, E> 
{
    E mapToDbEntity(D domainObject);
    D mapToDomainObject(E dbEntity);
}
