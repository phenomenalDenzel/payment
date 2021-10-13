package com.qds.ulinzi.service;

import com.qds.ulinzi.entity.ExternalAccount;
import com.qds.ulinzi.repository.ExternalAccountRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class DefaultExternalAccountService {

    @Inject
    ExternalAccountRepository externalAccountRepository;

    @Transactional
    public ExternalAccount save(ExternalAccount monnifyAccount) {
        externalAccountRepository.persist(monnifyAccount);
        return monnifyAccount;
    }
}
