package ssp.order.domain.serviceorder;

import lombok.Value;
import ssp.common.domain.Identity;

@Value
public class ServiceOrderId implements Identity
{
    private final String value;
}
