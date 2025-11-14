package com.bank.authorization.exeptionhandler;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
