package ssp.account.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.account.application.AccountService;
import ssp.account.application.command.RegisterAccount;
import ssp.account.domain.Account;
import ssp.account.domain.AccountRepository;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountController
{
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    
    @GetMapping
    public List<Account> getAllAccounts()
    {
        return accountRepository.findAll();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody RegisterAccount command)
    {
        log.info("[Account Subdomain]: API endpoint [POST /api/v1/accounts] was called");
        
        accountService.registerNewAccount(command);
    }
}
