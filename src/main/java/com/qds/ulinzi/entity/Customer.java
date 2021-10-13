package com.qds.ulinzi.entity;

import com.qds.ulinzi.service.strategy.Generators;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {
    @NotBlank
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "customer_code")
    private String customerCode;
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Column(name = "last_name")
    private String lastName;
    @NotBlank
    @Column(name = "account_reference")
    private String accountReference;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ExternalAccount> externalAccounts = new ArrayList<>();


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

    public void generateCustomerCode(){
        String currentTimestampInSeconds = String.valueOf(System.currentTimeMillis());
        this.customerCode = Generators.randomGenerateCode(4,currentTimestampInSeconds,null);
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

    public String getAccountReference() {
        return accountReference;
    }

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }


    public List<ExternalAccount> getExternalAccounts() {
        return externalAccounts;
    }

    public void setExternalAccounts(ExternalAccount externalAccounts){
        this.externalAccounts.add(externalAccounts);

    }
}
