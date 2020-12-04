package com.paysys.account.application.port.in;

import com.paysys.account.domain.TransferStatus;

import java.math.BigDecimal;

public interface SendMoneyUseCase {
    TransferStatus transferFunds(String fromAccount, String toAccount, BigDecimal amount);

    TransferStatus transferFunds(String toAccount, BigDecimal amount);
}
