package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Account;

import java.util.*;

public interface IAccountService {

    List<Account> findAll();

    Optional<Account> findById(String accountId);

    Account save(Account account);

    void deleteById(String accountId);
}
