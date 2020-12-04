package com.paysys.account.application.port.out;

import com.paysys.account.domain.Account;

import java.util.Optional;

public interface AccountRepositoryPort {

    Optional<Account> findById(String id);
}
