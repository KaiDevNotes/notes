package ssp.common.application.event.account;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import ssp.common.application.event.Event;

@Value
@Builder
public class AccountRegistred implements Event
{
    @NonNull
    private final String accountId;
    @NonNull
    private final String email;
    @NonNull
    private final String password;
}
