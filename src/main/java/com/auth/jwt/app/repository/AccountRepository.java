package com.auth.jwt.app.repository;

import com.auth.jwt.app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByActiveTrue();
    Optional<Account> findByAccountIdAndActiveTrue(String accountId);
}