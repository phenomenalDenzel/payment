package com.qds.ulinzi.monify;

import io.quarkus.oidc.client.OidcClientConfig;
import io.quarkus.oidc.client.OidcClientException;
import io.quarkus.oidc.client.Tokens;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.HttpRequest;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Closeable;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

import static io.quarkus.oidc.common.runtime.OidcCommonUtils.clientSecret;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;

@Singleton
public class MonnifyOidcClient implements Closeable {

    private static final Logger LOG = Logger.getLogger(MonnifyOidcClient.class);
    private static final String AUTHORIZATION_HEADER;

    @Inject
    Vertx vertx;

    WebClient client;

    private final String clientSecretBasicAuthScheme;
    private final OidcClientConfig oidcClientConfig;

    @PostConstruct
    void initialize() {
        this.client = WebClient.create(vertx, new WebClientOptions()
                                                    .setSsl(true).setTrustAll(true));
    }

    public MonnifyOidcClient() {
        this.oidcClientConfig = new OidcClientConfig();
        oidcClientConfig.setId(ConfigProvider.getConfig().getValue("oidc.id",String.class));
        oidcClientConfig.setAuthServerUrl(ConfigProvider.getConfig().getValue("oidc.auth-server-url",String.class));
        oidcClientConfig.setClientId(ConfigProvider.getConfig().getValue("oidc.client-id",String.class));
        oidcClientConfig.getCredentials().setSecret(ConfigProvider.getConfig().getValue("oidc.client-secret",String.class));
        oidcClientConfig.setRefreshTokenTimeSkew(Duration.ofSeconds(5));
        this.clientSecretBasicAuthScheme = "Basic " + Base64.getEncoder()
                                                            .encodeToString((oidcClientConfig.getClientId()
                                                                                             .get() + ":" + clientSecret(oidcClientConfig.credentials)).getBytes(StandardCharsets.UTF_8));
    }

    public Uni<Tokens> getTokens() {
        return this.getJsonResponse();
    }

    public Uni<Tokens> refreshTokens(String refreshToken) {
        return getTokens();
    }

    private Uni<Tokens> getJsonResponse() {
        return Uni.createFrom()
                  .deferred(() -> {
                      HttpRequest<Buffer> request = client.postAbs(oidcClientConfig.authServerUrl.get());
                      request.putHeader(CONTENT_TYPE, HttpHeaders.APPLICATION_X_WWW_FORM_URLENCODED.toString());
                      if (clientSecretBasicAuthScheme != null) {
                          request.putHeader(AUTHORIZATION_HEADER, clientSecretBasicAuthScheme);
                      }

                      Uni<HttpResponse<Buffer>> response = request.send()
                                                                  .onFailure(ConnectException.class)
                                                                  .retry()
                                                                  .atMost(3L);
                      return response.onItem()
                                     .transform(this::emitGrantTokens);
                  });
    }

    private Tokens emitGrantTokens(HttpResponse<Buffer> resp) {
        if (resp.statusCode() == 200) {
            JsonObject json = resp.bodyAsJsonObject();
            JsonObject responseBody = json.getJsonObject("responseBody");
            String accessToken = responseBody.getString("accessToken");
            Long accessTokenExpiresIn = responseBody.getLong("expiresIn");
            Long accessTokenExpiresAt = Instant.now().getEpochSecond() + accessTokenExpiresIn;
            return new Tokens(accessToken, accessTokenExpiresAt, this.oidcClientConfig.refreshTokenTimeSkew.orElse(null), null);
        } else {
            String errorMessage = resp.bodyAsString();
            LOG.debugf("%s OidcClient has failed to complete the request: error message: %s", new Object[]{
                    this.oidcClientConfig.getId().get(), errorMessage});
            throw new OidcClientException(errorMessage);
        }
    }


    static {
        AUTHORIZATION_HEADER = AUTHORIZATION;
    }

    public void close() throws IOException {
        this.client.close();
    }

}

