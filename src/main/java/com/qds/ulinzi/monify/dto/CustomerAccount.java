package com.qds.ulinzi.monify.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qds.ulinzi.service.dto.AccountDTO;

import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerAccount {
    private UUID id;
    private String customerEmail;
    private String customerName;
    private String accountReference;
    private String accountName;
    private String contractCode;
    private String currencyCode;
    private String collectionChannel;
    private String reservationReference;
    private String reservedAccountType;
    private String status;
    private String restrictPaymentSource;
    private List<AccountDTO> accounts;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCollectionChannel() {
        return collectionChannel;
    }

    public void setCollectionChannel(String collectionChannel) {
        this.collectionChannel = collectionChannel;
    }

    public String getReservationReference() {
        return reservationReference;
    }

    public void setReservationReference(String reservationReference) {
        this.reservationReference = reservationReference;
    }

    public String getReservedAccountType() {
        return reservedAccountType;
    }

    public void setReservedAccountType(String reservedAccountType) {
        this.reservedAccountType = reservedAccountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRestrictPaymentSource() {
        return restrictPaymentSource;
    }

    public void setRestrictPaymentSource(String restrictPaymentSource) {
        this.restrictPaymentSource = restrictPaymentSource;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
