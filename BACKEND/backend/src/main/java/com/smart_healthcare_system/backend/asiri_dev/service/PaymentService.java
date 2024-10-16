package com.smart_healthcare_system.backend.asiri_dev.service;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentRequest;
import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentResponse;
import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import com.smart_healthcare_system.backend.asiri_dev.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //Get All payments
    public List<PaymentResponse> findAllPayments() {
        List <Payment> payments = paymentRepository.findAll();
        return payments.stream().map(this::mapToPaymentResponse).toList();
    }
    public PaymentResponse mapToPaymentResponse(Payment payment){
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .paymentCategory(payment.getPaymentCategory())
                .paymentAmount(payment.getPaymentAmount())
                .paymentType(payment.getPaymentType())
                .cardholderName(payment.getCardholderName())
                .cardNumber(payment.getCardNumber())
                .expiryDate(payment.getExpiryDate())
                .cvv(payment.getCvv())
                .remarks(payment.getCvv())
                .status(payment.getStatus())
                .build();

    }
}
