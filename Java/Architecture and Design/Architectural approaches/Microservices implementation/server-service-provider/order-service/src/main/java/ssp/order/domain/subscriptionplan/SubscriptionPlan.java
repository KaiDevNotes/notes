package ssp.order.domain.subscriptionplan;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import ssp.common.domain.Aggregate;

@Builder
@Getter
@ToString
public class SubscriptionPlan implements Aggregate<SubscriptionPlanId>
{
    @Builder.Default
    private SubscriptionPlanId id = new SubscriptionPlanId(UUID.randomUUID().toString());
    
    @NonNull
    private String name;
    
    @NonNull
    private BigDecimal priceInUsd;
    
    @Builder.Default
    private Status status = Status.INACTIVE;
    
    public void activate()
    {
        status = Status.ACTIVE;
    }
    
    public void deactivate()
    {
        status = Status.INACTIVE;
    }
    
    public boolean isActive()
    {
        return this.status == Status.ACTIVE;
    }
    
    public boolean isNotActive()
    {
        return !isActive();
    }
    
    public static enum Status
    {
        ACTIVE, INACTIVE;
    }
}
