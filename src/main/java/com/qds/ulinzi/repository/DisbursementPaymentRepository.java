package com.qds.ulinzi.repository;

import com.qds.ulinzi.entity.DisbursementPayment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class DisbursementPaymentRepository implements PanacheRepository<DisbursementPayment> {
    public Optional<DisbursementPayment> findByTransactionReference(String transactionReference){
        return find("transactionReference", transactionReference).firstResultOptional();
    }
}
