package com.qds.ulinzi.repository;

import com.qds.ulinzi.entity.InvalidDisbursementPayment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InvalidDisbursementPaymentRepository implements PanacheRepository<InvalidDisbursementPayment> {
}
