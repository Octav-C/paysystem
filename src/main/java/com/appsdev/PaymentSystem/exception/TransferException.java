package com.appsdev.PaymentSystem.exception;

public class TransferException extends RuntimeException {
    public TransferException(String errorMessage) {
        super(errorMessage);
    }
}
