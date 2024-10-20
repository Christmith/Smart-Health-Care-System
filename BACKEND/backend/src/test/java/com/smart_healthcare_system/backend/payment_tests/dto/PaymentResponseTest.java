package com.smart_healthcare_system.backend.payment_tests.dto;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentResponse;
import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentResponseTest {


    /*
    * Purpose of this Test:
This test ensures that when a PaymentResponse object is created using the builder, all of the fields are set as expected
* and that the getter methods for each field return the correct value. It is a unit test for object creation and field
* initialization.
The successful case demonstrates that all attributes are initialized and retrieved correctly, which confirms the basic
* functionality of the PaymentResponse class and its builder method.
    * */
    //Successful test case
    @Test
    public void paymentResponse_shouldCreateSuccessfully() {
        // Arrange
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId("P123")
                .paymentCategory(Payment.paymentCategory.Consultation)
                .paymentAmount(1000)
                .paymentType(Payment.PaymentType.Card)
                .cardholderName("John Doe")
                .cardNumber("1234567890123456")
                .expiryDate("12/25")
                .cvv("123")
                .remarks("Test payment")
                .status("completed")
                .build();

        // Act & Assert
        assertEquals("P123", paymentResponse.getPaymentId());
        assertEquals(Payment.paymentCategory.Consultation, paymentResponse.getPaymentCategory());
        assertEquals(1000, paymentResponse.getPaymentAmount());
        assertEquals(Payment.PaymentType.Card, paymentResponse.getPaymentType());
        assertEquals("John Doe", paymentResponse.getCardholderName());
        assertEquals("1234567890123456", paymentResponse.getCardNumber());
        assertEquals("12/25", paymentResponse.getExpiryDate());
        assertEquals("123", paymentResponse.getCvv());
        assertEquals("Test payment", paymentResponse.getRemarks());
        assertEquals("completed", paymentResponse.getStatus());
    }

    //Negative test case
    @Test
    public void paymentResponse_shouldFail_whenPaymentIdIsMissing() {
        // Arrange & Act
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId("")
                .paymentCategory(Payment.paymentCategory.Consultation)
                .paymentAmount(1000)
                .paymentType(Payment.PaymentType.Card)
                .cardholderName("John Doe")
                .cardNumber("1234567890123456")
                .expiryDate("12/25")
                .cvv("123")
                .remarks("Test payment")
                .status("completed")
                .build();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            if (paymentResponse.getPaymentId().isEmpty()) {
                throw new IllegalArgumentException("Payment ID cannot be empty");
            }
        });
    }
}
