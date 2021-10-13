package com.qds.ulinzi.monify;

import com.qds.ulinzi.monify.dto.AccountCreationRequest;
import com.qds.ulinzi.monify.dto.AccountCreationResponse;
import com.qds.ulinzi.monify.dto.AccountRetrievalResponse;
import com.qds.ulinzi.monify.error.AbstractMonnifyException;
import com.qds.ulinzi.monify.error.MonnifyResponseMapper;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RegisterRestClient(configKey = "config.api.monnify")
@RegisterProvider(MonnifyClientRequestFilter.class)
@RegisterProvider(value = MonnifyResponseMapper.class, priority = 50)
@Path("/")
public interface MonnifyGateway {

    @POST
    @Path("v2/bank-transfer/reserved-accounts")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    AccountCreationResponse reserveAccount(AccountCreationRequest accountCreationRequest) throws AbstractMonnifyException;

    @GET
    @Path("v2/bank-transfer/reserved-accounts/{accountReference}")
    @Produces(MediaType.APPLICATION_JSON)
    AccountRetrievalResponse getReserveAccount(@PathParam("accountReference") String accountReference) throws AbstractMonnifyException;
}
