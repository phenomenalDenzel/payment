package com.qds.ulinzi.service.dto;

import java.util.List;
import java.util.UUID;

public class CustomerDTO {
    public UUID id;

    public String customerEmail;

    public String customerCode;

    public String firstName;

    public String lastName;

    public String accountReference;

    public List<ExternalAccountDTO> externalAccounts;
}
