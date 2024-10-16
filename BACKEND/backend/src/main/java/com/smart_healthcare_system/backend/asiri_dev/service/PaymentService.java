package com.smart_healthcare_system.backend.asiri_dev.service;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentRequest;
import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import com.smart_healthcare_system.backend.asiri_dev.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;

    //Create Payment
    public void createPayment(PaymentRequest paymentRequest) {
        Payment payment = Payment.builder()
                .paymentCategory(paymentRequest.getPaymentCategory())
                .paymentAmount(paymentRequest.getPaymentAmount())
                .paymentType(paymentRequest.getPaymentType())
                .cardholderName(paymentRequest.getCardholderName())
                .cardNumber(paymentRequest.getCardNumber())
                .expiryDate(paymentRequest.getExpiryDate())
                .cvv(paymentRequest.getCvv())
                .remarks(paymentRequest.getRemarks())
                .status(paymentRequest.getStatus())
                .build();

        paymentRepository.save(payment);
        log.info("Payment created");
    }
}
