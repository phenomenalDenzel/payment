package com.qds.ulinzi.monify;

import io.quarkus.oidc.client.Tokens;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class MonnifyTokensProducer {

    @Inject
    MonnifyOidcClient oidcClient;
    @Inject
    @ConfigProperty(name = "quarkus.oidc-client.early-tokens-acquisition")
    boolean earlyTokenAcquisition;

    MonnifyTokenHelper tokensHelper = new MonnifyTokenHelper();

    public MonnifyTokensProducer() {
    }

    @PostConstruct
    public void initTokens() {
        if (this.earlyTokenAcquisition) {
            this.tokensHelper.initTokens(this.oidcClient);
        }

    }

    public Uni<Tokens> getTokens() {
        return this.tokensHelper.getTokens(this.oidcClient);
    }
}
