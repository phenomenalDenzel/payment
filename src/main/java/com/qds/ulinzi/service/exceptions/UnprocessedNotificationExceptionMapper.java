package com.qds.ulinzi.service.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnprocessedNotificationExceptionMapper implements ExceptionMapper<UnprocessedNotificationException> {
    @Override
    public Response toResponse(UnprocessedNotificationException ex) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),ex.getMessage()))
                .build();
    }
}
