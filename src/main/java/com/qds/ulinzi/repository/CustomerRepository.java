package com.qds.ulinzi.repository;

import com.qds.ulinzi.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, UUID> {
    public Optional<Customer> findByCustomerEmail(String customerEmail){
        return find("customerEmail",customerEmail).singleResultOptional();
    }

    public Optional<Customer> findByCustomerEmailAndCustomerCode(String customerEmail, String customerCode){
        return find("customerEmail = ?1 and customerCode = ?2",customerEmail,customerCode).singleResultOptional();
    }
}
