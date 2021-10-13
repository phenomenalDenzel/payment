package com.qds.ulinzi.monify.dto;

public abstract class GenericResponse<T> {
    private String requestSuccessful;
    private String responseMessage;
    private String responseCode;
    private T responseBody;

    public String getRequestSuccessful() {
        return requestSuccessful;
    }

    public void setRequestSuccessful(String requestSuccessful) {
        this.requestSuccessful = requestSuccessful;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }
}
