package com.qds.ulinzi.service.dto;

public class CustomerAccountDetails {
    private String customerEmail;
    private String customerCode;
    private String defaultAccountName;
    private String defaultAccountNumber;
    private String defaultBankName;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getDefaultAccountName() {
        return defaultAccountName;
    }

    public void setDefaultAccountName(String defaultAccountName) {
        this.defaultAccountName = defaultAccountName;
    }

    public String getDefaultAccountNumber() {
        return defaultAccountNumber;
    }

    public void setDefaultAccountNumber(String defaultAccountNumber) {
        this.defaultAccountNumber = defaultAccountNumber;
    }

    public String getDefaultBankName() {
        return defaultBankName;
    }

    public void setDefaultBankName(String defaultBankName) {
        this.defaultBankName = defaultBankName;
    }
}
