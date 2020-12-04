package com.paysys.account.adapter.out.persistence;

import com.paysys.account.application.port.out.AccountRepositoryPort;
import com.paysys.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public class AccountRepositoryAdapter implements AccountRepositoryPort {

    @Autowired
    private AccountRepositoryJpa accountRepositoryJpa;

    @Override
    public Optional<Account> findById(String id) {
        return accountRepositoryJpa.findById(id);
    }

    @Repository
    interface AccountRepositoryJpa extends JpaRepository<Account, String> {

    }
}
