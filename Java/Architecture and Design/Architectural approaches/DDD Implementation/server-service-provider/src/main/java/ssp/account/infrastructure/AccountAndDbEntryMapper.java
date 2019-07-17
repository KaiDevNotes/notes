package ssp.account.infrastructure;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ssp.account.domain.Account;
import ssp.account.domain.AccountId;
import ssp.common.infrastructure.persistence.AggregateAndDbEntryMapper;

@Component
public class AccountAndDbEntryMapper implements AggregateAndDbEntryMapper<Account, AccountId, AccountDbEntry>
{
    @Override
    public AccountDbEntry mapToDbEntry(Account account)
    {
        if (account == null) return null;
               
        return AccountDbEntry.builder()
            .id(UUID.fromString(account.getId().getValue()))
            .email(account.getEmail())
            .password(account.getPassword())
            .role(account.getRole())
            .build();
    }
    
    @Override
    public Account mapToAggregate(AccountDbEntry dbEntry)
    {
        if (dbEntry == null) return null;
        
        return Account.builder()
            .id(new AccountId(dbEntry.getId().toString()))
            .email(dbEntry.getEmail())
            .password(dbEntry.getPassword())
            .role(dbEntry.getRole())
            .build();
    }
}
