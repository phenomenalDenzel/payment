package com.qds.ulinzi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Entity
public class MonnifyAccount extends ExternalAccount {
    private String accountName;
    private String currencyCode;
    private String collectionChannel;
    private String reservationReference;
    private String reservedAccountType;
    private Instant createdDate;
    private String restrictPaymentSource;
    @OneToMany(mappedBy = "monnifyAccount", cascade = CascadeType.ALL)
    private List<Account> accounts;

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

    @Override
    public Instant getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getRestrictPaymentSource() {
        return restrictPaymentSource;
    }

    public void setRestrictPaymentSource(String restrictPaymentSource) {
        this.restrictPaymentSource = restrictPaymentSource;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
