package com.qds.ulinzi.service.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CreateAccountRequest {
    @Email
    private String customerEmail;

    private String bvn;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

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
}
