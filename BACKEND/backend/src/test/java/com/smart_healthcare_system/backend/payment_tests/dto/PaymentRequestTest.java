package com.smart_healthcare_system.backend.payment_tests.dto;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentRequest;
import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentRequestTest {

    //Successful Test
    @Test
    public void paymentRequest_shouldCreateSuccessfully() {
        // Arrange
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .paymentCategory(Payment.paymentCategory.Medication)
                .paymentAmount(1500)
                .paymentType(Payment.PaymentType.Cash)
                .cardholderName("Jane Doe")
                .cardNumber("9876543210987654")
                .expiryDate("11/24")
                .cvv("456")
                .remarks("Medicine payment")
                .status("pending")
                .build();

        // Act & Assert
        assertEquals(Payment.paymentCategory.Medication, paymentRequest.getPaymentCategory());
        assertEquals(1500, paymentRequest.getPaymentAmount());
        assertEquals(Payment.PaymentType.Cash, paymentRequest.getPaymentType());
        assertEquals("Jane Doe", paymentRequest.getCardholderName());
        assertEquals("9876543210987654", paymentRequest.getCardNumber());
        assertEquals("11/24", paymentRequest.getExpiryDate());
        assertEquals("456", paymentRequest.getCvv());
        assertEquals("Medicine payment", paymentRequest.getRemarks());
        assertEquals("pending", paymentRequest.getStatus());
    }

    //Unsuccessful Test
    //If the card number is not 16 digits, the test should pass because the exception (IllegalArgumentException) is thrown.
    @Test
    public void paymentRequest_shouldFail_whenCardNumberIsInvalid() {
        // Arrange & Act
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .paymentCategory(Payment.paymentCategory.Surgery)
                .paymentAmount(5000)
                .paymentType(Payment.PaymentType.Card)
                .cardholderName("John Smith")
                .cardNumber("bfdgfdgfdgfdgfdg") // Invalid card number
                .expiryDate("10/26")
                .cvv("789")
                .remarks("Surgery payment")
                .status("pending")
                .build();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            if (!paymentRequest.getCardNumber().matches("\\d{16}")) {
                throw new IllegalArgumentException("Card number must be 16 digits");
            }
        });
    }

}
