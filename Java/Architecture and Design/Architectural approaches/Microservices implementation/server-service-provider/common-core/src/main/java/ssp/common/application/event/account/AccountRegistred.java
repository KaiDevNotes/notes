package ssp.common.application.event.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ssp.common.application.event.Event;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegistred implements Event
{
    @NonNull
    private String accountId;
    @NonNull
    private String email;
    @NonNull
    private String password;
}
