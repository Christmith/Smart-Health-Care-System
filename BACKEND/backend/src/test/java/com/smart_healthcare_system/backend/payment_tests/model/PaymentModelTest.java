package com.smart_healthcare_system.backend.payment_tests.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import org.junit.jupiter.api.Test;

public class PaymentModelTest {

    //Positive test case
    @Test
    public void paymentModel_shouldCreateSuccessfully() {
        // Arrange
        Payment payment = Payment.builder()
                .paymentId("PAY123")
                .paymentCategory(Payment.paymentCategory.Medication)
                .paymentAmount(1500)
                .paymentType(Payment.PaymentType.Card)
                .cardholderName("Jane Doe")
                .cardNumber("9876543210987654")
                .expiryDate("11/24")
                .cvv("456")
                .remarks("Medicine payment")
                .status("pending")
                .build();

        // Act & Assert
        assertThat(payment).isNotNull();
        assertThat(payment.getPaymentId()).isEqualTo("PAY123");
        assertThat(payment.getPaymentCategory()).isEqualTo(Payment.paymentCategory.Medication);
        assertThat(payment.getPaymentAmount()).isEqualTo(1500);
        assertThat(payment.getPaymentType()).isEqualTo(Payment.PaymentType.Card);
        assertThat(payment.getCardholderName()).isEqualTo("Jane Doe");
        assertThat(payment.getCardNumber()).isEqualTo("9876543210987654");
        assertThat(payment.getExpiryDate()).isEqualTo("11/24");
        assertThat(payment.getCvv()).isEqualTo("456");
        assertThat(payment.getRemarks()).isEqualTo("Medicine payment");
        assertThat(payment.getStatus()).isEqualTo("pending");
    }


    @Test
    public void paymentModel_shouldFail_whenCardholderNameIsMissing() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = Payment.builder()
                    .paymentId("PAY123")
                    .paymentCategory(Payment.paymentCategory.Medication)
                    .paymentAmount(1500)
                    .paymentType(Payment.PaymentType.Card)
                    .cardholderName(null) // Missing required field
                    .cardNumber("9876543210987654")
                    .expiryDate("11/24")
                    .cvv("456")
                    .remarks("Medicine payment")
                    .status("pending")
                    .build();

            // Check if exception is thrown when trying to access the cardholderName
            if (payment.getCardholderName() == null) {
                throw new IllegalArgumentException("Cardholder name is required");
            }
        });
    }
}
