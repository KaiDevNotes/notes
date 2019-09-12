package ssp.order.domain.subscriptionplan;

import lombok.Value;
import ssp.common.domain.Identity;

@Value
public class SubscriptionPlanId implements Identity
{
    private final String value;
}
