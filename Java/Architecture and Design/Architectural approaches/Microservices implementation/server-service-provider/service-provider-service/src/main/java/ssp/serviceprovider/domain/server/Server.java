package ssp.serviceprovider.domain.server;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import ssp.common.domain.Aggregate;

@Builder
@Getter
@ToString
public class Server implements Aggregate<ServerId>
{
    @Builder.Default
    private ServerId id = new ServerId(UUID.randomUUID().toString());
    
    @NonNull
    private String ip;
    
    @NonNull
    private String port;
    
    @NonNull
    private Location location;
    
    @Builder.Default
    private Status status = Status.INACTIVE;
    
    public void activate()
    {
        status = Status.ACTIVE;
    }
    
    public void deactivate()
    {
        status = Status.INACTIVE;
    }
    
    public boolean isActive()
    {
        return this.status == Status.ACTIVE;
    }
    
    public boolean isNotActive()
    {
        return !isActive();
    }
    
    public static enum Status
    {
        ACTIVE, INACTIVE;
    }
}
