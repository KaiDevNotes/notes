package root.application;

import java.util.List;
import java.util.UUID;

public interface DomainObjectGateway<D> 
{
    void save(D domainObject);
    D findById(UUID domainObjectId);
    List<D> findAll();
    void delete(UUID domainObjectId);
}
