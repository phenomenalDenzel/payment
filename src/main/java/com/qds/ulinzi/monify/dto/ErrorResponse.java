package com.qds.ulinzi.monify.dto;

public class ErrorResponse {
    private String requestSuccessful;
    private String responseMessage;
    private String responseCode;

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

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "requestSuccessful='" + requestSuccessful + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", responseCode='" + responseCode + '\'' +
                '}';
    }
}
