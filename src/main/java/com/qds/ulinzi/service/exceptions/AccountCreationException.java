package com.qds.ulinzi.service.exceptions;

public class AccountCreationException extends RuntimeException{
    public AccountCreationException() {
        super("ACCOUNT_CREATION_EXCEPTION");
    }

    public AccountCreationException(String message) {
        super(message);
    }
}
