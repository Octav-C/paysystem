package com.paysys.account.application.port.out;

import com.paysys.account.domain.TransactionHistory;

public interface TransactionHistoryRepositoryPort {

    TransactionHistory save (TransactionHistory transactionHistory);
}
