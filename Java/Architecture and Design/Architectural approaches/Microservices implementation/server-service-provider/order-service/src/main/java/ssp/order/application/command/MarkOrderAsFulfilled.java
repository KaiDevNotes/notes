package ssp.order.application.command;

import lombok.Builder;
import lombok.Value;
import ssp.common.application.command.Command;

@Value
@Builder
public class MarkOrderAsFulfilled implements Command
{
    private final String orderId;
    private final String serviceUserId;
}
