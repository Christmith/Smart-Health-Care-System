package com.smart_healthcare_system.backend.asiri_dev.service;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentRequest;
import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentResponse;

import java.util.List;

public interface PaymentServiceInterface {
    void createPayment(PaymentRequest paymentRequest);

    List<PaymentResponse> findAllPayments();

    void updatePayment(String paymentId, PaymentRequest paymentRequest);

    void deletePayment(String paymentId);
}
