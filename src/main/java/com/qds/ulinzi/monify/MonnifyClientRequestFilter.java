package com.qds.ulinzi.monify;

import org.jboss.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Singleton;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Collections;

@Provider
@Singleton
@Priority(1000)
public class MonnifyClientRequestFilter extends MonnifyTokensProducer implements ClientRequestFilter {
    private static final Logger LOG = Logger.getLogger(MonnifyClientRequestFilter.class);

    public void filter(ClientRequestContext requestContext) throws IOException {
        try {
            String accessToken = this.getAccessToken();
            requestContext.getHeaders().put("Authorization", Collections.singletonList("Bearer " + accessToken));
        } catch (Exception var4) {
            LOG.debugf("Access token is not available, aborting the request with HTTP 401 error: %s", var4.getMessage());
            requestContext.abortWith(Response.status(401)
                                             .build());
        }

    }

    private String getAccessToken() {
        return this.getTokens()
                   .await()
                   .indefinitely()
                   .getAccessToken();
    }
}
