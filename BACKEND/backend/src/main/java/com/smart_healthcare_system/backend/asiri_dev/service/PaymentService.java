package com.smart_healthcare_system.backend.asiri_dev.service;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentRequest;
import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentResponse;
import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import com.smart_healthcare_system.backend.asiri_dev.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements PaymentServiceInterface {
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
                .remarks(paymentRequest.getRemarks() != null ? paymentRequest.getRemarks() : "")
                .status(paymentRequest.getStatus() != null ? paymentRequest.getStatus() : "progress")
                .build();

        paymentRepository.save(payment);
        log.info("Payment created");
    }

    //Get All payments
    public List<PaymentResponse> findAllPayments() {
        List <Payment> payments = paymentRepository.findAll();
        return payments.stream().map(this::mapToPaymentResponse).toList();
    }
    public PaymentResponse mapToPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .paymentCategory(payment.getPaymentCategory())
                .paymentAmount(payment.getPaymentAmount())
                .paymentType(payment.getPaymentType())
                .cardholderName(payment.getCardholderName())
                .cardNumber(payment.getCardNumber())
                .expiryDate(payment.getExpiryDate())
                .cvv(payment.getCvv())
                .remarks(payment.getRemarks())
                .status(payment.getStatus())
                .build();

    }

    //Update Payment
    public void updatePayment(String paymentId, PaymentRequest paymentRequest){
        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,"Payment not found"));

        Payment updatedPayment = Payment.builder()
                .paymentId(existingPayment.getPaymentId())
                .paymentCategory(existingPayment.getPaymentCategory())
                .paymentAmount(existingPayment.getPaymentAmount())
                .paymentType(existingPayment.getPaymentType())
                .cardholderName(existingPayment.getCardholderName())
                .cardNumber(existingPayment.getCardNumber())
                .expiryDate(existingPayment.getExpiryDate())
                .cvv(existingPayment.getCvv())
                .remarks(paymentRequest.getRemarks())
                .status(paymentRequest.getStatus())
                .build();
        paymentRepository.save(updatedPayment);
        log.info("Payment updated");
    }

    //Delete Payment
    public void deletePayment(String paymentId){
        if(paymentRepository.existsById(paymentId)){
            paymentRepository.deleteById(paymentId);
            log.info("Payment deleted");
        }
        else{
            throw new ResponseStatusException(NOT_FOUND,"Payment not found");
        }
    }

}
