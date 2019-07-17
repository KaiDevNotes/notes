package ssp.order.application.command;

import javax.validation.constraints.Size;

import lombok.Value;
import ssp.common.application.command.Command;

@Value
public class CreateOrder implements Command
{   
    @Size(min = 36, max = 36)
    private final String accountId;  
    @Size(min = 36, max = 36)
    private final String serverId;
    @Size(min = 36, max = 36)
    private final String subscriptionPlanId;
}
