package com.paysys.account.adapter.out.persistence;

import com.paysys.account.application.port.out.TransactionHistoryRepositoryPort;
import com.paysys.account.domain.TransactionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public class TransactionHistoryRepositoryAdapter implements TransactionHistoryRepositoryPort {

    @Autowired
    private TransactionHistoryRepositoryJpa transactionHistoryRepositoryJpa;

    @Override
    public TransactionHistory save(TransactionHistory transactionHistory) {
        return transactionHistoryRepositoryJpa.save(transactionHistory);
    }

    @Repository
    public interface TransactionHistoryRepositoryJpa extends JpaRepository<TransactionHistory, String> {

    }
}
