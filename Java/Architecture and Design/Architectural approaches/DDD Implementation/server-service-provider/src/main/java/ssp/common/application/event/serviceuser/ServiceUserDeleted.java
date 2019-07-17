package ssp.common.application.event.serviceuser;

import lombok.Value;
import ssp.common.application.event.Event;

@Value
public class ServiceUserDeleted implements Event
{
    private final String serviceUserId;
}
