package com.qds.ulinzi.repository;

import com.qds.ulinzi.entity.InvalidSettlementPayment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InvalidSettlementPaymentRepository implements PanacheRepository<InvalidSettlementPayment> {
}
