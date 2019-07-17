package ssp.common.application.event.serviceuser;

import lombok.Builder;
import lombok.Value;
import ssp.common.application.event.Event;

@Value
@Builder
public class ServiceUserCreated implements Event
{
    private final String orderId;
    private final String serviceUserId;
    private final String serverId;
}
