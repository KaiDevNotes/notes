package ssp.common.application.event.serviceuser;

import lombok.Builder;
import lombok.Value;
import ssp.common.application.event.Event;

@Value
@Builder
public class ServiceUserMoved implements Event
{
    private final String userId;
    private final String previousServerId;
    private final String currentServerId;
}
