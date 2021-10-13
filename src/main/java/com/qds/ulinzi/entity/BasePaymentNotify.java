package com.qds.ulinzi.entity;

import com.qds.ulinzi.entity.enums.PaymentResult;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
public abstract class BasePaymentNotify extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(generator = "paymentIdGenerator")
    @GenericGenerator(name = "paymentIdGenerator", strategy = "uuid2")
    private UUID id;

    @Column(name="transaction_reference")
    private String transactionReference;

    @Column(name="payment_reference")
    private String paymentReference;

    @Column(name="response_body",columnDefinition = "TEXT")
    private String responseBody;

    private boolean valid;

    @Column(name = "processed_date")
    private Instant processedDate;

    @Enumerated(EnumType.STRING)
    private PaymentResult result;

    private boolean duplicate;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Instant getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Instant processedDate) {
        this.processedDate = processedDate;
    }

    public PaymentResult getResult() {
        return result;
    }

    public void setResult(PaymentResult result) {
        this.result = result;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }
}
