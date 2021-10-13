package com.qds.ulinzi.service.dto;

public class ResponseDTO {
    public boolean success;
    public int code;
    public String customerEmail;

    public ResponseDTO(boolean success, int code, String customerEmail){
        this.success = success;
        this.code = code;
        this.customerEmail = customerEmail;
    }
}
