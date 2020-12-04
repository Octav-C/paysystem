package com.paysys.account.application.service;

import com.paysys.account.application.port.out.AccountRepositoryPort;
import com.paysys.account.application.port.out.TransactionHistoryRepositoryPort;
import com.paysys.account.domain.Account;
import com.paysys.account.domain.TransactionHistory;
import com.paysys.account.domain.TransferStatus;
import com.paysys.account.exception.TransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private static final int BIG_DECIMAL_LESS_THAN = -1;

    private static final String ERR_NO_FUNDS = "NO FUNDS AVAILABLE ON ACCOUNT -> [%s]";

    @Autowired
    private AccountRepositoryPort accountRepositoryPort;

    @Autowired
    private TransactionHistoryRepositoryPort transactionHistoryRepositoryPort;

    @Transactional(rollbackOn = TransferException.class)
    public TransferStatus updateBalanceToAccount(final String accountNumber, final BigDecimal amount) {
        final Optional<Account> account = accountRepositoryPort.findById(accountNumber);

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

        //TODO: Populate From To Correctly
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .transactionId(UUID.randomUUID().toString())
                .fromEmail("From")
                .toEmail("To")
                .txnDate(LocalDateTime.now())
                .amount(amount)
                .build();

        transactionHistoryRepositoryPort.save(transactionHistory);

        return TransferStatus.SUCCESS;
    }
}
