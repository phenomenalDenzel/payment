package com.qds.ulinzi.monify.error;

import com.qds.ulinzi.monify.dto.ErrorResponse;

public class AccountAlreadyExistException extends AbstractMonnifyException{
    public AccountAlreadyExistException(ErrorResponse errorResponse){
        super(errorResponse);
    }
}
