package com.bank.profile.exception;

/**
 * Исключение для отсутствующих сущностей.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}