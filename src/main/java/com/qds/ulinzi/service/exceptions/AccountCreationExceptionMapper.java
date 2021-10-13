package com.qds.ulinzi.service.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountCreationExceptionMapper implements ExceptionMapper<AccountCreationException> {
    @Override
    public Response toResponse(AccountCreationException ex) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(new ErrorMessage(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(),ex.getMessage()))
                .build();
    }
}
