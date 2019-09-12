package ssp.account.infrastructure;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ssp.account.domain.Account;
import ssp.account.domain.AccountId;
import ssp.account.domain.AccountRepository;
import ssp.common.infrastructure.persistence.AbstractAggregateJpaRepository;

@Component
@RequiredArgsConstructor
public class AccountJpaRepository extends AbstractAggregateJpaRepository<Account, AccountId, AccountDbEntry> implements AccountRepository
{
    private final AccountDbEntryRepository dbEntryRepository;
    private final AccountAndDbEntryMapper mapper;
    
    @Override
    protected AccountDbEntryRepository getDbEntryRepository()
    {
        return dbEntryRepository;
    }
    
    @Override
    protected AccountAndDbEntryMapper getMapper()
    {
        return mapper;
    }
}
