package com.qds.ulinzi.service;

import com.qds.ulinzi.entity.DisbursementPayment;
import com.qds.ulinzi.entity.InvalidDisbursementPayment;
import com.qds.ulinzi.entity.InvalidSettlementPayment;
import com.qds.ulinzi.entity.SettlementPayment;
import com.qds.ulinzi.entity.enums.PaymentResult;
import com.qds.ulinzi.repository.DisbursementPaymentRepository;
import com.qds.ulinzi.repository.InvalidDisbursementPaymentRepository;
import com.qds.ulinzi.repository.InvalidSettlementPaymentRepository;
import com.qds.ulinzi.repository.SettlementPaymentRepository;
import com.qds.ulinzi.security.SHA512HashingStrategy;
import com.qds.ulinzi.service.exceptions.UnprocessedNotificationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class MonnifyPaymentService {
    Logger LOG = Logger.getLogger(MonnifyPaymentService.class);

    @ConfigProperty(name = "oidc.client-secret")
    String monnifyClientSecret;

    @Inject
    DisbursementPaymentRepository disbursementRepository;

    @Inject
    SettlementPaymentRepository settlementRepository;

    @Inject
    InvalidDisbursementPaymentRepository invalidDisbursementRepository;

    @Inject
    InvalidSettlementPaymentRepository invalidSettlementRepository;

    @Transactional
    public void handleDisbursement(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String keysToHash = extractKeysForHashing(jsonObject);
            String hashedValue = SHA512HashingStrategy.generateHash(keysToHash);
            if (!hashedValue.equals(jsonObject.getString("transactionHash"))) {
                LOG.error("Hashed value for disbursement is invalid with result: " + hashedValue);
                InvalidDisbursementPayment transaction = new InvalidDisbursementPayment();
                transaction.setTransactionReference(jsonObject.getString("transactionReference"));
                transaction.setPaymentReference(jsonObject.getString("paymentReference"));
                transaction.setResponseBody(response);
                transaction.setValid(Boolean.FALSE);

                invalidDisbursementRepository.persist(transaction);
            } else {
                Optional<DisbursementPayment> existedTransactionOpt = disbursementRepository.findByTransactionReference(jsonObject.getString("transactionReference"));
                DisbursementPayment transaction = new DisbursementPayment();
                transaction.setTransactionReference(jsonObject.getString("transactionReference"));
                transaction.setPaymentReference(jsonObject.getString("paymentReference"));
                transaction.setResponseBody(response);
                transaction.setValid(Boolean.TRUE);
                if (existedTransactionOpt.isPresent()) {
                    transaction.setDuplicate(Boolean.TRUE);
                    transaction.setResult(PaymentResult.SKIP);
                } else {
                    transaction.setDuplicate(Boolean.FALSE);
                }
                disbursementRepository.persist(transaction);
            }

        } catch (Exception ex) {
            LOG.error("Could not handle " +
                    "disbursement request with exception {}", ex);
            throw new UnprocessedNotificationException();
        }
    }

    @Transactional
    public void handleSettlement(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String keysToHash = extractKeysForHashing(jsonObject);
            String hashedValue = SHA512HashingStrategy.generateHash(keysToHash);

            if (!hashedValue.equals(jsonObject.getString("transactionHash"))) {
                LOG.error("Hashed value for settlement is invalid with result: " + hashedValue);
                InvalidSettlementPayment transaction = new InvalidSettlementPayment();
                transaction.setTransactionReference(jsonObject.getString("transactionReference"));
                transaction.setPaymentReference(jsonObject.getString("paymentReference"));
                transaction.setResponseBody(response);
                transaction.setValid(Boolean.FALSE);

                invalidSettlementRepository.persist(transaction);
            } else {
                Optional<SettlementPayment> existedTransactionOpt = settlementRepository.findByTransactionReference(jsonObject.getString("transactionReference"));
                SettlementPayment transaction = new SettlementPayment();
                transaction.setTransactionReference(jsonObject.getString("transactionReference"));
                transaction.setPaymentReference(jsonObject.getString("paymentReference"));
                transaction.setResponseBody(response);
                transaction.setValid(Boolean.TRUE);
                if (existedTransactionOpt.isPresent()) {
                    transaction.setDuplicate(Boolean.TRUE);
                    transaction.setResult(PaymentResult.SKIP);
                } else {
                    transaction.setDuplicate(Boolean.FALSE);
                }
                settlementRepository.persist(transaction);
            }
        } catch (Exception ex) {
            LOG.error("Could not handle " +
                    "settlement request with exception {}", ex);
            throw new UnprocessedNotificationException();
        }
    }

    private String extractKeysForHashing(JSONObject jsonObject) {
        String transactionReference = jsonObject.getString("transactionReference");
        String paymentReference = jsonObject.getString("paymentReference");
        String amountPaid = jsonObject.getString("amountPaid");
        String paidOn = jsonObject.getString("paidOn");

        return monnifyClientSecret + "|" + paymentReference + "|" + amountPaid + "|" + paidOn + "|" + transactionReference;
    }
}
