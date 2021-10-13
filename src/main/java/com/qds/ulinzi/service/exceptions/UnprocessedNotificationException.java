package com.qds.ulinzi.service.exceptions;

public class UnprocessedNotificationException extends RuntimeException{

    public UnprocessedNotificationException(){
        super("Unable to Process Notification");
    }

    public UnprocessedNotificationException(String message){
        super(message);
    }
}
