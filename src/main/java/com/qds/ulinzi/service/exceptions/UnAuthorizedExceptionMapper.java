package com.qds.ulinzi.service.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnAuthorizedExceptionMapper implements ExceptionMapper<UnAuthorizedException> {

    @Override
    public Response toResponse(UnAuthorizedException ex) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorMessage(Response.Status.UNAUTHORIZED.getStatusCode(),ex.getMessage()))
                .build();
    }
}
