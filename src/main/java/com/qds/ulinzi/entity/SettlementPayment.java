package com.qds.ulinzi.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "settlement_payment", indexes = @Index(name = "smt_index", columnList = "transaction_reference, payment_reference"))
public class SettlementPayment extends BasePaymentNotify implements Serializable {
}
