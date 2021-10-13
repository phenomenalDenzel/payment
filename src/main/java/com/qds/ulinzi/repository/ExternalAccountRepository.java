package com.qds.ulinzi.repository;

import com.qds.ulinzi.entity.ExternalAccount;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ExternalAccountRepository implements PanacheRepositoryBase<ExternalAccount, UUID> {

    public Optional<ExternalAccount> findByCustomerEmail(String customerEmail) {
        return find("customer.customerEmail",customerEmail).singleResultOptional();
    }
}
