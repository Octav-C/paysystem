package com.paysys.account.exception;

public class TransferException extends RuntimeException {
    public TransferException(String errorMessage) {
        super(errorMessage);
    }
}
