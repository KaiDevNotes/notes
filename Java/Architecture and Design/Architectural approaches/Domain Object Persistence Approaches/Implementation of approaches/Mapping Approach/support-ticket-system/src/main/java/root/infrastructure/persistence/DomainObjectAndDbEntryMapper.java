package root.infrastructure.persistence;

import root.domain.DomainObject;

public interface DomainObjectAndDbEntryMapper<D extends DomainObject, E extends DbEntry>
{
    E mapToDbEntry(D domainObject);
    D mapToDomainObject(E dbEntry);
}
