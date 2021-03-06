package ssp.common.application.event.order;

import lombok.Builder;
import lombok.Value;
import ssp.common.application.event.Event;

@Value
@Builder
public class OrderPayed implements Event
{
    private final String orderId;
    private final String serverId;
}
