package com.qds.ulinzi.monify.error;

import com.qds.ulinzi.monify.dto.ErrorResponse;

public class UnknownMonnifyErrorException extends AbstractMonnifyException{
    public UnknownMonnifyErrorException(ErrorResponse content) {
        super(content);
    }
}
