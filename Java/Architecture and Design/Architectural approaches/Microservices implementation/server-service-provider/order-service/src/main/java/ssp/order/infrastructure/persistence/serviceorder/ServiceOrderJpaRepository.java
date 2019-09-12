package ssp.order.infrastructure.persistence.serviceorder;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ssp.common.infrastructure.persistence.AbstractAggregateJpaRepository;
import ssp.order.domain.serviceorder.ServiceOrder;
import ssp.order.domain.serviceorder.ServiceOrderId;
import ssp.order.domain.serviceorder.ServiceOrderRepository;

@Component
@RequiredArgsConstructor
public class ServiceOrderJpaRepository extends AbstractAggregateJpaRepository<ServiceOrder, ServiceOrderId, ServiceOrderDbEntry> implements ServiceOrderRepository
{
    private final ServiceOrderDbEntryRepository dbEntryRepository;
    private final ServiceOrderAndDbEntryMapper mapper;
    
    @Override
    protected ServiceOrderDbEntryRepository getDbEntryRepository()
    {
        return dbEntryRepository;
    }
    
    @Override
    protected ServiceOrderAndDbEntryMapper getMapper()
    {
        return mapper;
    }
}
