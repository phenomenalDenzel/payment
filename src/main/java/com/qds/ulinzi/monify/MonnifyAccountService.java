package com.qds.ulinzi.monify;

import com.qds.ulinzi.entity.ExternalAccount;
import com.qds.ulinzi.entity.MonnifyAccount;
import com.qds.ulinzi.monify.dto.AccountCreationRequest;
import com.qds.ulinzi.monify.dto.AccountCreationResponse;
import com.qds.ulinzi.monify.dto.AccountRetrievalResponse;
import com.qds.ulinzi.monify.dto.CustomerAccount;
import com.qds.ulinzi.monify.error.AbstractMonnifyException;
import com.qds.ulinzi.monify.error.AccountAlreadyExistException;
import com.qds.ulinzi.monify.mapper.MonnifyAccountResponseMapper;
import com.qds.ulinzi.monify.mapper.MonnifyRequestMapper;
import com.qds.ulinzi.service.ExternalAccountService;
import com.qds.ulinzi.service.dto.AccountCreationDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Default
public class MonnifyAccountService implements ExternalAccountService {
    Logger LOG = Logger.getLogger(MonnifyAccountService.class);

    @Inject
    @RestClient
    MonnifyGateway monnifyGateway;
    @Inject
    MonnifyRequestMapper monnifyRequestMapper;
    @Inject
    MonnifyAccountResponseMapper monnifyAccountResponseMapper;

    @ConfigProperty(name = "monnify.contractid")
    String contractCode;
    @ConfigProperty(name = "payment.account.create.allbanks")
    String getAllBanksString;
    @ConfigProperty(name = "payment.account.create.preferredbanks")
    String preferredBanksString;

    @Override
    public Optional<ExternalAccount> createAccount(AccountCreationDTO accountCreationDTO){
        List<String> preferredBanks = Arrays.asList(preferredBanksString.split(","));
        boolean getAllBanks = Boolean.parseBoolean(getAllBanksString);
        AccountCreationRequest accountCreationRequest = monnifyRequestMapper.toEntity(accountCreationDTO);
        accountCreationRequest.setContractCode(contractCode);
        accountCreationRequest.setCurrencyCode("NGN");
        accountCreationRequest.setGetAllAvailableBanks(getAllBanks);
        if (!getAllBanks) {
            accountCreationRequest.setPreferredBanks(preferredBanks);
        }
        accountCreationRequest.setCustomerName(accountCreationDTO.getFirstName() + " " + accountCreationDTO.getLastName());

        try {
            AccountCreationResponse accountCreationResponse = monnifyGateway.reserveAccount(accountCreationRequest);
            MonnifyAccount monnifyAccount = getMonnifyAccount(accountCreationResponse.getResponseBody());
            return Optional.of(monnifyAccount);
        } catch (AccountAlreadyExistException ex) {
            LOG.info("Account already exist, trying to get the already existing account for accountReference: "+accountCreationRequest.getAccountReference());
            try {
                AccountRetrievalResponse reservedAccount = monnifyGateway.getReserveAccount(accountCreationRequest.getAccountReference());
                MonnifyAccount monnifyAccount = getMonnifyAccount(reservedAccount.getResponseBody());
                return Optional.of(monnifyAccount);
            } catch (AbstractMonnifyException e) {
                LOG.error("Could not get account on monnify for " + accountCreationRequest.getCustomerEmail(), ex);
                return Optional.empty();
            }
        } catch (AbstractMonnifyException ex) {
            LOG.error("Could not create account on monnify for " + accountCreationRequest.getCustomerEmail(), ex);
            return Optional.empty();
        }
    }

    private MonnifyAccount getMonnifyAccount(CustomerAccount responseBody) {
        MonnifyAccount monnifyAccount = monnifyAccountResponseMapper.toEntity(responseBody);
        monnifyAccount.getAccounts()
                .forEach(account -> account.setMonnifyAccount(monnifyAccount));
        return monnifyAccount;
    }
}
