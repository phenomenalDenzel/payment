package com.qds.ulinzi.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "disbursement_payment", indexes = @Index(name = "dbt_index", columnList = "transaction_reference, payment_reference"))
public class DisbursementPayment extends BasePaymentNotify implements Serializable {

}
