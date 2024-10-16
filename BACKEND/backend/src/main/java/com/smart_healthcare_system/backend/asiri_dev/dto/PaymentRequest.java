package com.smart_healthcare_system.backend.asiri_dev.dto;

import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    private String paymentCategory;
    private int paymentAmount;
    private Payment.PaymentType paymentType;
    private String cardholderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String remarks;
    private String status;
}
