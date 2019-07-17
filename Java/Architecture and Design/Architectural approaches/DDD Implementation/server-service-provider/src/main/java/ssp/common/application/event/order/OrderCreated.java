package ssp.common.application.event.order;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;
import ssp.common.application.event.Event;

@Value
@Builder
public class OrderCreated implements Event
{
    private final String orderId;
    private final String accountId;    
    private final String serverId;
    private final BigDecimal priceInUsd;
}
