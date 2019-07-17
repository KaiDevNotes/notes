package ssp.order.application.command;

import lombok.Value;
import ssp.common.application.command.Command;

@Value
public class MarkOrderAsPayed implements Command
{
    private final String orderId;
}
