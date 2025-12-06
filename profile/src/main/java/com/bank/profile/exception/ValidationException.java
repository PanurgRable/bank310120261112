package com.bank.profile.exception;

/**
 * Исключение бизнес-валидации.
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}