package com.qds.ulinzi.monify.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qds.ulinzi.monify.dto.ErrorResponse;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.annotation.Priority;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Priority(4000)
public class MonnifyResponseMapper implements ResponseExceptionMapper {
    @Override
    public Throwable toThrowable(Response response) {
        ErrorResponse body = getBody(response);

        if (body.getResponseMessage().equals("You can not reserve two accounts with the same reference"))
            return new AccountAlreadyExistException(body);

        return new UnknownMonnifyErrorException(body);
    }

    private ErrorResponse getBody(Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue((InputStream) response.getEntity(), ErrorResponse.class);
        } catch (IOException ex) {
            throw new RuntimeException("Unable to create monnify error response", ex);
        }
    }
}
