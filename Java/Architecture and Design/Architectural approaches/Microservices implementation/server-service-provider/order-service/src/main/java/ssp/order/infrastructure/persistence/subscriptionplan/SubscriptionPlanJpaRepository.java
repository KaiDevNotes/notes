package ssp.order.infrastructure.persistence.subscriptionplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ssp.common.infrastructure.persistence.AbstractAggregateJpaRepository;
import ssp.order.domain.subscriptionplan.SubscriptionPlan;
import ssp.order.domain.subscriptionplan.SubscriptionPlanId;
import ssp.order.domain.subscriptionplan.SubscriptionPlanRepository;

@Component
@RequiredArgsConstructor
public class SubscriptionPlanJpaRepository extends AbstractAggregateJpaRepository<SubscriptionPlan, SubscriptionPlanId, SubscriptionPlanDbEntry> implements SubscriptionPlanRepository
{
    private final SubscriptionPlanDbEntryRepository dbEntryRepository;
    private final SubscriptionPlanAndDbEntryMapper mapper;
    
    @Override
    protected SubscriptionPlanDbEntryRepository getDbEntryRepository()
    {
        return dbEntryRepository;
    }
    
    @Override
    protected SubscriptionPlanAndDbEntryMapper getMapper()
    {
        return mapper;
    }
}
