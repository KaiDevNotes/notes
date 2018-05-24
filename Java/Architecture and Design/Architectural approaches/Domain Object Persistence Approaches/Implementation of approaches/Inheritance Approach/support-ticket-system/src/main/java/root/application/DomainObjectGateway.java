package root.application;

import java.util.List;

public interface DomainObjectGateway<D> 
{
    void save(D domainObject);
    D findById(String domainObjectId);
    List<D> findAll();
    void delete(String domainObjectId);
}
