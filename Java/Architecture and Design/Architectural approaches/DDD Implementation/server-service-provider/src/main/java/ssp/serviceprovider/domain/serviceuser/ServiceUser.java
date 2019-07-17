package ssp.serviceprovider.domain.serviceuser;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import ssp.common.domain.Aggregate;
import ssp.serviceprovider.domain.server.ServerId;

@Builder
@Getter
@ToString
public class ServiceUser implements Aggregate<ServiceUserId>
{
    private static final String ASSIGNMENT_ERROR_MSG = "ServiceUser cannot be assigned to server [%s] since it is already assigned to server [%s].";
    private static final String UNASSIGNMENT_ERROR_MSG = "ServiceUser cannot be unassigned from server [%s] since the ServiceUser is not assigned to the server.";
    
    @Builder.Default
    private ServiceUserId id = new ServiceUserId(UUID.randomUUID().toString());
    
    @NonNull
    private String login;
    
    @NonNull
    private String password;
    
    private ServerId serverId;
    
    public void assignToServer(ServerId serverId)
    {        
        if (this.serverId != null)
        {
            throw new RuntimeException(String.format(ASSIGNMENT_ERROR_MSG, serverId.getValue(), this.serverId.getValue()));
        }        
        this.serverId = serverId;
    }
    
    public void unassignFromServer(ServerId serverId)
    {
        if (this.serverId == null || !this.serverId.equals(serverId))
        {
            throw new RuntimeException(String.format(UNASSIGNMENT_ERROR_MSG, serverId.getValue()));
        }         
        this.serverId = null;
    }
}
