package com.qds.ulinzi.security;

import com.qds.ulinzi.service.dto.AuthDTO;
import com.qds.ulinzi.service.dto.AuthResponse;
import com.qds.ulinzi.service.exceptions.UnAuthorizedException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@RequestScoped
public class AuthenticationUtils {
    @ConfigProperty(name = "auth.keycloak-url")
    String keyCloakUrl;
    @ConfigProperty(name = "auth.realm")
    String realm;

    public AuthResponse authenticateUser(AuthDTO auth){
        AccessTokenResponse tokenResponse = getAccessTokenForUser(auth.clientId,auth.clientSecret);
        if (tokenResponse.getToken() == null) {
            throw new UnAuthorizedException("Invalid Credentials");
        } else {
            return AuthResponse.asBuilder()
                    .accessToken(tokenResponse.getToken())
                    .refreshToken(tokenResponse.getRefreshToken())
                    .expiresIn(tokenResponse.getExpiresIn());
        }
    }

    public AccessTokenResponse getAccessTokenForUser(String clientId, String clientSecret){
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keyCloakUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
        return keycloak.tokenManager().getAccessToken();
    }
}
