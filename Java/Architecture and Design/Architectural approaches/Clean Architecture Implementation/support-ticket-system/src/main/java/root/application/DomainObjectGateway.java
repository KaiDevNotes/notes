package root.application;

import java.util.List;

public interface DomainObjectGateway<D> 
{
    D save(D domainObject);
    D findById(String domainObjectId);
    List<D> findAll();
    void delete(String domainObjectId);
}
