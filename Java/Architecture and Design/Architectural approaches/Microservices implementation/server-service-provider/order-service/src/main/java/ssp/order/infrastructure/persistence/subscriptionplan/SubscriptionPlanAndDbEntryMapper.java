package ssp.order.infrastructure.persistence.subscriptionplan;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ssp.common.infrastructure.persistence.AggregateAndDbEntryMapper;
import ssp.order.domain.subscriptionplan.SubscriptionPlan;
import ssp.order.domain.subscriptionplan.SubscriptionPlanId;

@Component
public class SubscriptionPlanAndDbEntryMapper implements AggregateAndDbEntryMapper<SubscriptionPlan, SubscriptionPlanId, SubscriptionPlanDbEntry>
{
    @Override
    public SubscriptionPlanDbEntry mapToDbEntry(SubscriptionPlan plan)
    {
        if (plan == null) return null;
               
        return SubscriptionPlanDbEntry.builder()
            .id(UUID.fromString(plan.getId().getValue()))
            .name(plan.getName())
            .priceInUsd(plan.getPriceInUsd())
            .status(plan.getStatus())
            .build();
    }
    
    @Override
    public SubscriptionPlan mapToAggregate(SubscriptionPlanDbEntry dbEntry)
    {
        if (dbEntry == null) return null;
        
        return SubscriptionPlan.builder()
            .id(new SubscriptionPlanId(dbEntry.getId().toString()))
            .name(dbEntry.getName())
            .priceInUsd(dbEntry.getPriceInUsd())
            .status(dbEntry.getStatus())
            .build();
    }
}
