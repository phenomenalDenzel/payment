package com.qds.ulinzi.repository;

import com.qds.ulinzi.entity.SettlementPayment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class SettlementPaymentRepository implements PanacheRepository<SettlementPayment> {
    public Optional<SettlementPayment> findByTransactionReference(String transactionReference){
        return find("transactionReference", transactionReference).firstResultOptional();
    }
}
