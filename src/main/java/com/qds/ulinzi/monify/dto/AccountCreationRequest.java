package com.qds.ulinzi.monify.dto;

import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountCreationRequest {
    private String customerEmail;
    private String accountReference;
    private String accountName;
    private String contractCode;
    private String bvn;
    private String customerName;
    private boolean getAllAvailableBanks;
    private String currencyCode;
    private List<String> preferredBanks;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public List<String> getPreferredBanks() {
        return preferredBanks;
    }

    public void setPreferredBanks(List<String> preferredBanks) {
        this.preferredBanks = preferredBanks;
    }
}
