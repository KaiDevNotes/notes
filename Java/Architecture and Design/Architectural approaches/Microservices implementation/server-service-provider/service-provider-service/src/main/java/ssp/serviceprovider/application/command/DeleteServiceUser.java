package ssp.serviceprovider.application.command;

import lombok.Value;
import ssp.common.application.command.Command;

@Value
public class DeleteServiceUser implements Command
{
    private final String serviceUserId;
}
