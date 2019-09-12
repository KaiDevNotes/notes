package ssp.common.application.event.serviceuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssp.common.application.event.Event;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUserDeleted implements Event
{
    private String serviceUserId;
}
