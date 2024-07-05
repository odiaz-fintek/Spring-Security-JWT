package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Account;
import com.auth.jwt.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AccountService implements IAccountService{

    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findByActiveTrue();
    }

    @Override
    public Optional<Account> findById(String accountId) {
        return accountRepository.findByAccountIdAndActiveTrue(accountId);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(String accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setActive(false); // Change this line to set active to false
            accountRepository.save(account);
        }
    }
}