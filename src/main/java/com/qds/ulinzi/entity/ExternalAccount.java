package com.qds.ulinzi.entity;

import com.qds.ulinzi.entity.enumaration.AccountStatus;
import com.qds.ulinzi.entity.enums.AccountType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@Entity
@Table(name="external_account")
public class ExternalAccount extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private String defaultAccountName;

    private String defaultAccountNumber;

    private String defaultBankName;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus status) {
        this.accountStatus = accountStatus;
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
