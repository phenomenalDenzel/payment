package com.qds.ulinzi.service.dto;

import java.time.Instant;

public class MonnifyAccountDTO extends ExternalAccountDTO{
    private String accountName;
    private String currencyCode;
    private String collectionChannel;
    private String reservationReference;
    private String reservedAccountType;
    private String status;
    private Instant createdDate;
    private String restrictPaymentSource;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getRestrictPaymentSource() {
        return restrictPaymentSource;
    }

    public void setRestrictPaymentSource(String restrictPaymentSource) {
        this.restrictPaymentSource = restrictPaymentSource;
    }
}
