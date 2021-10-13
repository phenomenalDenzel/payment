package com.qds.ulinzi.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "invalid_disbursement_payment", indexes = @Index(name = "idbt_index", columnList = "transaction_reference, payment_reference"))
public class InvalidDisbursementPayment extends BasePaymentNotify implements Serializable{

}
