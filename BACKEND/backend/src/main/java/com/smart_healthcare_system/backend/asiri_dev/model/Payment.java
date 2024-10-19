package com.smart_healthcare_system.backend.asiri_dev.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document(collection = "Payments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Payment {
    @Id
    private String paymentId;
    private paymentCategory paymentCategory;
    private int paymentAmount;
    private PaymentType paymentType;
    @NonNull
    private String cardholderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String remarks;
    private String status;

    public enum PaymentType{
        Cash,
        Card,
        Insurance
    }
    public enum paymentCategory{
        Consultation,
        Medication,
        Surgery,
        LabTest,
        Room_Rental,
        Miscellaneous,
    }
}
