package com.qds.ulinzi.service.dto;

import com.qds.ulinzi.entity.enumaration.AccountStatus;

import java.util.UUID;

public class ExternalAccountDTO {
    private UUID id;
    private UUID customerId;
    private AccountStatus accountStatus;
    private String defaultAccountName;
    private String defaultAccountNumber;
    private String defaultBankName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
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
