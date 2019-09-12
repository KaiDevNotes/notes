package ssp.serviceprovider.application.command;

import lombok.Builder;
import lombok.Value;
import ssp.common.application.command.Command;

@Value
@Builder
public class MoveServiceUser implements Command
{
    private String serviceUserId;
    private String currentServerId;
    private String targetServerId;
}
