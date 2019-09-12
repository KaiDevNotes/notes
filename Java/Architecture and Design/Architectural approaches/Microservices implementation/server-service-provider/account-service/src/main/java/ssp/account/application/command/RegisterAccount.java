package ssp.account.application.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Value;
import ssp.common.application.command.Command;

@Value
public class RegisterAccount implements Command
{
    @Email
    private final String email;
    @Size(min = 7, max = 32)
    private final String password; 
}
