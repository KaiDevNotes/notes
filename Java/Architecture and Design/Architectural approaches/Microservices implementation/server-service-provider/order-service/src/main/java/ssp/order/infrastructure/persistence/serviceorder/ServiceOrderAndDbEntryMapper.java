package ssp.order.infrastructure.persistence.serviceorder;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ssp.common.infrastructure.persistence.AggregateAndDbEntryMapper;
import ssp.order.domain.serviceorder.AccountId;
import ssp.order.domain.serviceorder.ServerId;
import ssp.order.domain.serviceorder.ServiceOrder;
import ssp.order.domain.serviceorder.ServiceOrderId;
import ssp.order.domain.serviceorder.ServiceUserId;

@Component
public class ServiceOrderAndDbEntryMapper implements AggregateAndDbEntryMapper<ServiceOrder, ServiceOrderId, ServiceOrderDbEntry>
{
    @Override
    public ServiceOrderDbEntry mapToDbEntry(ServiceOrder order)
    {
        if (order == null) return null;
               
        return ServiceOrderDbEntry.builder()
            .id(UUID.fromString(order.getId().getValue()))
            .accountId(order.getAccountId().getValue())
            .serverId(order.getServerId().getValue())
            .serviceUserId(order.getServiceUserId() == null ? null : order.getServiceUserId().getValue())
            .priceInUsd(order.getPriceInUsd())
            .status(order.getStatus())
            .build();
    }
    
    @Override
    public ServiceOrder mapToAggregate(ServiceOrderDbEntry dbEntry)
    {
        if (dbEntry == null) return null;
        
        return ServiceOrder.builder()
            .id(new ServiceOrderId(dbEntry.getId().toString()))
            .accountId(new AccountId(dbEntry.getAccountId()))
            .serverId(new ServerId(dbEntry.getServerId()))
            .serviceUserId(dbEntry.getServiceUserId() == null ? null : new ServiceUserId(dbEntry.getServiceUserId()))
            .priceInUsd(dbEntry.getPriceInUsd())
            .status(dbEntry.getStatus())
            .build();
    }
}
