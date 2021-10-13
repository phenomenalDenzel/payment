package com.qds.ulinzi.service;

import com.qds.ulinzi.entity.Customer;
import com.qds.ulinzi.entity.ExternalAccount;
import com.qds.ulinzi.monify.MonnifyAccountService;
import com.qds.ulinzi.repository.ExternalAccountRepository;
import com.qds.ulinzi.service.dto.AccountCreationDTO;
import com.qds.ulinzi.service.dto.CustomerAccountDetails;
import com.qds.ulinzi.service.dto.CustomerDTO;
import com.qds.ulinzi.service.exceptions.AccountCreationException;
import com.qds.ulinzi.service.exceptions.CustomerAlreadyExistException;
import com.qds.ulinzi.service.mapper.CustomerAccountDetailsMapper;
import com.qds.ulinzi.service.mapper.CustomerMapper;
import com.qds.ulinzi.service.mapper.CustomerToAccountCreationMapper;
import com.qds.ulinzi.service.strategy.DefaultAccountReferenceCodeGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;


@ApplicationScoped
public class AccountService {
    Logger LOG = Logger.getLogger(MonnifyAccountService.class);
    @Inject
    CustomerToAccountCreationMapper customerToAccountCreationMapper;
    @Inject
    CustomerMapper customerMapper;
    @Inject
    ExternalAccountService externalAccountService;
    @Inject
    ExternalAccountRepository externalAccountRepository;
    @Inject
    DefaultExternalAccountService defaultExternalAccountService;
    @Inject
    CustomerService customerService;
    @Inject
    DefaultAccountReferenceCodeGenerator accountReferenceCodeGenerator;
    @Inject
    CustomerAccountDetailsMapper customerAccountDetailsMapper;


    public CustomerAccountDetails createCustomerWithAccount(AccountCreationDTO accountCreationDTO){
        Optional<Customer> existingCustomer = customerService.retrieveCustomer(accountCreationDTO.getCustomerEmail());
        if(accountExistFor(existingCustomer)) {
            LOG.error("Customer Already Exist for email "+accountCreationDTO.getCustomerEmail());
            Optional<CustomerAccountDetails> accountDetails = getCustomerDetailsByCustomerEmail(existingCustomer.get().getCustomerEmail());
            return accountDetails.orElseThrow(CustomerAlreadyExistException::new);
        }else if(existingCustomer.isPresent()){
            Customer customer = existingCustomer.get();
            accountCreationDTO.setAccountReference(customer.getAccountReference());
            return createExternalAccountForCustomer(accountCreationDTO, customer);
        }else {
            accountCreationDTO.setAccountReference(accountReferenceCodeGenerator.generate(accountCreationDTO));
            CustomerDTO customerDTO = customerToAccountCreationMapper.toEntity(accountCreationDTO);
            Customer customer = customerService.save(customerDTO);
            return createExternalAccountForCustomer(accountCreationDTO, customer);
        }
    }

    public Optional<CustomerAccountDetails> getCustomerDetailsByCustomerEmail(String customerEmail){
        Optional<ExternalAccount> optionalExternalAccount = externalAccountRepository.findByCustomerEmail(customerEmail);
        if(optionalExternalAccount.isPresent()){
            ExternalAccount externalAccount = optionalExternalAccount.get();
            CustomerAccountDetails accountDetails = customerAccountDetailsMapper.toDto(externalAccount);
            accountDetails.setCustomerCode(externalAccount.getCustomer().getCustomerCode());
            accountDetails.setCustomerEmail(externalAccount.getCustomer().getCustomerEmail());

            return Optional.of(accountDetails);
        }

        return Optional.empty();
    }

    private CustomerAccountDetails createExternalAccountForCustomer(AccountCreationDTO accountCreationDTO, Customer customer) {
        accountCreationDTO.setAccountName("Ulinzi_"+accountCreationDTO.getFirstName()+ "_"+accountCreationDTO.getLastName());
        Optional<ExternalAccount> externalAccountOptional = externalAccountService.createAccount(accountCreationDTO);
        if(externalAccountOptional.isEmpty())
            throw new AccountCreationException("Unable to create account for customer");
        ExternalAccount externalAccount = externalAccountOptional.get();
        externalAccount.setCustomer(customer);
        defaultExternalAccountService.save(externalAccount);

        CustomerAccountDetails customerAccountDetails = customerAccountDetailsMapper.toDto(externalAccount);
        customerAccountDetails.setCustomerCode(customer.getCustomerCode());
        customerAccountDetails.setCustomerEmail(customer.getCustomerEmail());
        return customerAccountDetails;
    }

    private boolean accountExistFor(Optional<Customer> existingCustomer) {
        return existingCustomer.isPresent() && CollectionUtils.isNotEmpty(existingCustomer.get().getExternalAccounts());
    }
}
