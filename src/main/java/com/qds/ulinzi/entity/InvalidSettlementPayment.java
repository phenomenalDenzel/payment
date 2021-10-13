package com.qds.ulinzi.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "invalid_settlement_payment", indexes = @Index(name = "ismt_index", columnList = "transaction_reference, payment_reference"))
public class InvalidSettlementPayment extends BasePaymentNotify implements Serializable {

}
