package com.qds.ulinzi.service.exceptions;

import javax.ws.rs.BadRequestException;

public class CustomerAlreadyExistException extends BadRequestException {
    public CustomerAlreadyExistException(String message) {
        super(message);
    }

    public CustomerAlreadyExistException() {
        super("CUSTOMER_ALREADY_EXIST");
    }
}
