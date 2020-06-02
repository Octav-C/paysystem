package com.appsdev.PaymentSystem.repository;

import com.appsdev.PaymentSystem.domain.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, String> {
}
