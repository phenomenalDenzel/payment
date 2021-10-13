package com.qds.ulinzi.monify;

import io.quarkus.oidc.client.Tokens;
import io.smallrye.mutiny.Uni;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class MonnifyTokenHelper {

    private volatile TokenRequestState tokenRequestState;
    private static final AtomicReferenceFieldUpdater<MonnifyTokenHelper, TokenRequestState> tokenRequestStateUpdater = AtomicReferenceFieldUpdater.newUpdater(MonnifyTokenHelper.class, TokenRequestState.class, "tokenRequestState");

    public void initTokens(MonnifyOidcClient oidcClient) {
        tokenRequestStateUpdater.set(this, new TokenRequestState(oidcClient.getTokens().await().indefinitely()));
    }

    public Uni<Tokens> getTokens(MonnifyOidcClient oidcClient) {
        TokenRequestState currentState = null;
        TokenRequestState newState = null;


        for (; ; ) {
            currentState = tokenRequestStateUpdater.get(this);
            if (currentState == null) {
                newState = new TokenRequestState(prepareUni(oidcClient.getTokens()));
                if (tokenRequestStateUpdater.compareAndSet(this, currentState, newState)) {
                    return newState.tokenUni;
                }
            } else if (currentState.tokenUni != null) {
                return currentState.tokenUni;
            } else {
                Tokens tokens = currentState.tokens;
                if (tokens.isAccessTokenExpired() || tokens.isAccessTokenWithinRefreshInterval()) {
                    newState = new TokenRequestState(prepareUni(
                            tokens.getRefreshToken() != null ? oidcClient.refreshTokens(tokens.getRefreshToken())
                                                             : oidcClient.getTokens()));
                    if (tokenRequestStateUpdater.compareAndSet(this, currentState, newState)) {
                        return newState.tokenUni;
                    }
                } else {
                    return Uni.createFrom()
                              .item(tokens);
                }
            }
        }
    }


    private Uni<Tokens> prepareUni(Uni<Tokens> tokens) {
        return tokens.onItemOrFailure()
                     .invoke((token, throwable) -> {
                         if (token != null) {
                             tokenRequestStateUpdater.set(MonnifyTokenHelper.this, new TokenRequestState(token));
                         } else {
                             tokenRequestStateUpdater.set(MonnifyTokenHelper.this, null);
                         }

                     });
    }

    static class TokenRequestState {
        final Tokens tokens;
        final Uni<Tokens> tokenUni;

        TokenRequestState(Tokens tokens) {
            this.tokens = tokens;
            this.tokenUni = null;
        }

        TokenRequestState(Uni<Tokens> tokensUni) {
            this.tokens = null;
            this.tokenUni = tokensUni;
        }
    }
}


