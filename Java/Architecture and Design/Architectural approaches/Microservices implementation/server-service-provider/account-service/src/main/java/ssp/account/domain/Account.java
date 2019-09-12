package ssp.account.domain;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import ssp.common.domain.Aggregate;

@Builder
@Getter
@ToString
public class Account implements Aggregate<AccountId>
{
    @Builder.Default
    private AccountId id = new AccountId(UUID.randomUUID().toString());
    
    @NonNull
    private String email;
    
    @NonNull
    private String password; 
    
    @Builder.Default
    private Role role = Role.USER;
    
    public static enum Role
    {
        USER, ADMIN;
    }
}
