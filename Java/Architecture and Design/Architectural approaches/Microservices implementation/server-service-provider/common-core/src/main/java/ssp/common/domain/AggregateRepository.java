package ssp.common.domain;

import java.util.List;
import java.util.Optional;

public interface AggregateRepository<A extends Aggregate<ID>, ID extends Identity>
{
    A save(A aggregate);
    
    Optional<A> findById(ID id);
    
    List<A> findAll();
    
    void delete(A aggregate);
}
