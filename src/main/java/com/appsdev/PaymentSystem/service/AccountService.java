package com.appsdev.PaymentSystem.service;

import com.appsdev.PaymentSystem.domain.Account;
import com.appsdev.PaymentSystem.domain.TransferStatus;
import com.appsdev.PaymentSystem.exception.TransferException;
import com.appsdev.PaymentSystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    private static final int BIG_DECIMAL_LESS_THAN = -1;

    private static final String ERR_NO_FUNDS = "NO FUNDS AVAILABLE ON ACCOUNT -> [%s]";
    @Autowired
    private AccountRepository accountRepository;

    @Transactional(rollbackOn = TransferException.class)
    public TransferStatus updateBalanceToAccount(final String accountNumber, final BigDecimal amount) {
        final Optional<Account> account = accountRepository.findById(accountNumber);
        final TransferStatus transferStatus;
        if (account.isPresent()) {
            return processUpdateToAccount(account.get(), amount);
        }
        return TransferStatus.ACCOUNT_NOT_FOUND;
    }

    private TransferStatus processUpdateToAccount(Account account, BigDecimal amount) {
        final boolean notSufficientBalance = account.getBalance().add(amount).compareTo(BigDecimal.ZERO) == BIG_DECIMAL_LESS_THAN;
        final BigDecimal initial = account.getBalance();

        if (notSufficientBalance) {
            throw new TransferException(String.format(ERR_NO_FUNDS, account.getAccountId()));
        }

        account.setBalance(initial.add(amount));

        return TransferStatus.SUCCESS;
    }
}
