package com.qds.ulinzi.monify.error;

import com.qds.ulinzi.monify.dto.ErrorResponse;

public abstract class AbstractMonnifyException extends Exception{
    private ErrorResponse content;

    public AbstractMonnifyException(ErrorResponse content){
        super(content.toString());
        this.content = content;
    }

    public ErrorResponse getContent() {
        return content;
    }

    public void setContent(ErrorResponse content) {
        this.content = content;
    }
}
