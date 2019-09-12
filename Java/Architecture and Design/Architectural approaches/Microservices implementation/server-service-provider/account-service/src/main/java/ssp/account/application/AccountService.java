package ssp.account.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.account.application.command.RegisterAccount;
import ssp.account.domain.Account;
import ssp.account.domain.AccountRepository;
import ssp.common.application.event.EventBus;
import ssp.common.application.event.account.AccountRegistred;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService
{
    private final AccountRepository accountRepository;
    private final EventBus eventBus;
    
    public void registerNewAccount(RegisterAccount command)
    {
        log.info("[Account Subdomain]: RegisterAccount command was received");
        
        Account account = accountRepository.save(
            Account.builder()
                   .email(command.getEmail())
                   .password(command.getPassword())
                   .build());
        
        log.info("[Account Subdomain]: AccountRegistred event will be published");
        
        eventBus.publish(
            AccountRegistred.builder()
                .accountId(account.getId().getValue())
                .email(account.getEmail())
                .password(account.getPassword())
                .build());
    }
}
