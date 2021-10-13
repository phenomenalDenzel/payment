package com.qds.ulinzi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "account")
public class Account extends AbstractEntity {

    @Column(name = "bank_code")
    private String bankCode;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "account_number")
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name="external_account_id", referencedColumnName = "id")
    private MonnifyAccount monnifyAccount;


    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public MonnifyAccount getMonnifyAccount() {
        return monnifyAccount;
    }

    public void setMonnifyAccount(MonnifyAccount monnifyAccount) {
        this.monnifyAccount = monnifyAccount;
    }
}
