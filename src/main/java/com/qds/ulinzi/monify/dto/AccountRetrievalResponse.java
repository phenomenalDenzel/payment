package com.qds.ulinzi.monify.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRetrievalResponse extends GenericResponse<RetrievedCustomerAccount> {

}
