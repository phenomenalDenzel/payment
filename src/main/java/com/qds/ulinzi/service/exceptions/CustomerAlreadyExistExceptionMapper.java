package com.qds.ulinzi.service.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomerAlreadyExistExceptionMapper implements ExceptionMapper<CustomerAlreadyExistException> {
    @Override
    public Response toResponse(CustomerAlreadyExistException ex) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(Response.Status.BAD_REQUEST.getStatusCode(),ex.getMessage()))
                .build();
    }
}
