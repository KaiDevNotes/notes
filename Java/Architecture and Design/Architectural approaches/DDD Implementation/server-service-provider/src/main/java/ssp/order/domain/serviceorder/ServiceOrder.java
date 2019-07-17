package ssp.order.domain.serviceorder;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import ssp.common.domain.Aggregate;

@Builder
@Getter
@ToString
public class ServiceOrder implements Aggregate<ServiceOrderId>
{
    private final static String STATUS_TRANSITION_ERROR_MSG_FORMAT = "ServiceOrder cannot be marked as [%s] because it has [%s] status";
    
    @Builder.Default
    private ServiceOrderId id = new ServiceOrderId(UUID.randomUUID().toString());
    
    @NonNull
    private AccountId accountId;
    
    @NonNull
    private ServerId serverId;
    
    private ServiceUserId serviceUserId;
    
    @NonNull
    private BigDecimal priceInUsd;
    
    @Builder.Default
    private Status status = Status.NEW;
    
    public void markAsPayed()
    {
        if (this.status != Status.NEW) 
        {    
            throw new RuntimeException(
                String.format(STATUS_TRANSITION_ERROR_MSG_FORMAT, Status.PAYED.name(), status.name()));
        }        
        this.status = Status.PAYED;
    }
    
    public void markAsFulfilled(ServiceUserId serviceUserId)
    {
        if (this.status != Status.PAYED) 
        {    
            throw new RuntimeException(
                String.format(STATUS_TRANSITION_ERROR_MSG_FORMAT, Status.FULFILLED.name(), status.name()));
        }        
        this.serviceUserId = serviceUserId;
        this.status = Status.FULFILLED;
    }
    
    public static enum Status
    {
        NEW, PAYED, FULFILLED;
    }
}
