package com.appsdev.PaymentSystem.service;

import com.appsdev.PaymentSystem.domain.Participant;
import com.appsdev.PaymentSystem.domain.TransferStatus;
import com.appsdev.PaymentSystem.exception.TransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class TransferService {

    private final static String SOURCE_ERROR = "Problem in transfer with source account  %s, [PROBLEM] -> %s";

    private final static String DST_ERROR = "Problem in transfer with destination account  %s, [PROBLEM] -> %s";

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private AccountService accountService;

    @Transactional(rollbackOn = TransferException.class)
    public TransferStatus transferFunds(String fromAccount, String toAccount, BigDecimal amount) {
        TransferStatus fromAccountTransaction = accountService.updateBalanceToAccount(fromAccount, amount.negate());
        TransferStatus toAccountTransaction = accountService.updateBalanceToAccount(toAccount, amount);

        //There are problems with one of transaction and we should find where
        if(!fromAccountTransaction.equals(TransferStatus.SUCCESS)) {
            throw new TransferException(String.format(SOURCE_ERROR, fromAccount, fromAccountTransaction));
        } else if (!toAccountTransaction.equals(TransferStatus.SUCCESS)) {
            throw new TransferException(String.format(DST_ERROR, toAccount, toAccountTransaction));
        }

        return TransferStatus.SUCCESS;
    }

    @Transactional(rollbackOn = TransferException.class)
    public TransferStatus transferFunds(String toAccount, BigDecimal amount) {
        TransferStatus toAccountTransaction = accountService.updateBalanceToAccount(toAccount, amount);

        //The problem is the Destination account processing
        return toAccountTransaction;
    }
}
