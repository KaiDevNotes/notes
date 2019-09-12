package ssp.common.application.event.serviceuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssp.common.application.event.Event;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUserMoved implements Event
{
    private String userId;
    private String previousServerId;
    private String currentServerId;
}
