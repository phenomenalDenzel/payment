package com.qds.ulinzi.service.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MonnifyExceptionMapper implements ExceptionMapper<MonnifyException> {

    @Override
    public Response toResponse(MonnifyException ex) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(new ErrorMessage(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(),ex.getMessage()))
                .build();
    }
}
