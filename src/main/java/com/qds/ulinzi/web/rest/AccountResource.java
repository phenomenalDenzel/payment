package com.qds.ulinzi.web.rest;

import com.qds.ulinzi.service.AccountService;
import com.qds.ulinzi.service.dto.AccountCreationDTO;
import com.qds.ulinzi.service.dto.CreateAccountRequest;
import com.qds.ulinzi.service.dto.CustomerAccountDetails;
import com.qds.ulinzi.service.exceptions.ErrorMessage;
import com.qds.ulinzi.service.mapper.AccountCreationMapper;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/account")
@Tag(ref="Account")
public class AccountResource {

    @Inject
    AccountService accountService;
    @Inject
    AccountCreationMapper accountCreationMapper;

    @POST
    @Path("/create")
    @RolesAllowed("INTERNAL")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponses({
            @APIResponse(description = "When customer account is created successfully",
                    name = "Created",
                    responseCode = "201",
                    content = {
                            @Content(mediaType="application/json")
                    })
    })
    public Response createAccount(CreateAccountRequest createAccountRequest){
        AccountCreationDTO accountCreationDTO = accountCreationMapper.toDto(createAccountRequest);
        CustomerAccountDetails accountDetails = accountService.createCustomerWithAccount(accountCreationDTO);
        return Response.status(Response.Status.CREATED).entity(accountDetails).build();
    }

    @GET
    @Path("customer-email/{email}")
    @RolesAllowed("INTERNAL")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses({
            @APIResponse(description = "When customer account details is retrieved successfully",
                    name = "Ok",
                    responseCode = "200",
                    content = {
                            @Content(mediaType="application/json")
                    })
    })
    public Response getAccountWithCustomerEmail(@PathParam("email")String customerEmail){
        Optional<CustomerAccountDetails> customerAccountDetailsOptional = accountService.getCustomerDetailsByCustomerEmail(customerEmail);
        if(customerAccountDetailsOptional.isEmpty())
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessage(Response.Status.NOT_FOUND.getStatusCode(),"NOT_FOUND"))
                    .build();
        CustomerAccountDetails customerAccountDetails = customerAccountDetailsOptional.get();
        return Response.ok(customerAccountDetails).build();
    }
}
