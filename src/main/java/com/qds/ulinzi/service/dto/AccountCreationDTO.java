package com.qds.ulinzi.service.dto;


import java.util.List;

public class AccountCreationDTO {
    private String customerEmail;
    private String bvn;
    private String firstName;
    private String lastName;
    private boolean getAllAvailableBanks;
    private String currencyCode;
    private String accountReference;
    private String accountName;
    private String contractCode;
    private List<String> preferredBanks;


    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isGetAllAvailableBanks() {
        return getAllAvailableBanks;
    }

    public void setGetAllAvailableBanks(boolean getAllAvailableBanks) {
        this.getAllAvailableBanks = getAllAvailableBanks;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAccountReference() {
        return accountReference;
    }

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public List<String> getPreferredBanks() {
        return preferredBanks;
    }

    public void setPreferredBanks(List<String> preferredBanks) {
        this.preferredBanks = preferredBanks;
    }
}
