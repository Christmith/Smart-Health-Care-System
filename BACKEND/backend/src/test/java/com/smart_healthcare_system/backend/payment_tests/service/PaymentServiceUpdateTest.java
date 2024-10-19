package com.smart_healthcare_system.backend.payment_tests.service;
import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentRequest;
import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import com.smart_healthcare_system.backend.asiri_dev.repository.PaymentRepository;
import com.smart_healthcare_system.backend.asiri_dev.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceUpdateTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Test Case 1: Update an existing payment with valid data
    @Test
    void testUpdatePayment_Success() {
        // Arrange
        String paymentId = "12345";
        Payment existingPayment = createTestPayment(paymentId);
        PaymentRequest paymentRequest = createTestPaymentRequest("Updated remarks", "completed");

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(existingPayment));

        // Act
        paymentService.updatePayment(paymentId, paymentRequest);

        // Assert
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    // Negative Test Case 1: Payment not found
    @Test
    void testUpdatePayment_PaymentNotFound() {
        // Arrange
        String paymentId = "nonExistingId";
        PaymentRequest paymentRequest = createTestPaymentRequest("Updated remarks", "completed");

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            paymentService.updatePayment(paymentId, paymentRequest);
        });

        assertEquals("Payment not found", exception.getReason());
    }

    // Negative Test Case 2: Payment ID is empty
    @Test
    void testUpdatePayment_EmptyPaymentId() {
        // Arrange
        String paymentId = "";
        PaymentRequest paymentRequest = createTestPaymentRequest("Updated remarks", "completed");

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            paymentService.updatePayment(paymentId, paymentRequest);
        });

        assertEquals("Payment not found", exception.getReason());
    }

    // Positive Test Case 2: Update with no remarks field provided
    @Test
    void testUpdatePayment_NoRemarks() {
        // Arrange
        String paymentId = "54321";
        Payment existingPayment = createTestPayment(paymentId);
        PaymentRequest paymentRequest = createTestPaymentRequest(null, "completed");

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(existingPayment));

        // Act
        paymentService.updatePayment(paymentId, paymentRequest);

        // Assert
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    // Negative Test Case 3: Invalid payment amount (e.g., negative amount)
    @Test
    void testUpdatePayment_InvalidAmount() {
        // Arrange
        String paymentId = "12345";
        Payment existingPayment = createTestPayment(paymentId);
        PaymentRequest paymentRequest = createTestPaymentRequest("Invalid update", "completed");
        paymentRequest.setPaymentAmount(-100); // Invalid amount

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(existingPayment));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.updatePayment(paymentId, paymentRequest);
        });

        assertEquals("Invalid payment amount", exception.getMessage());
    }

    // Helper method to create a test Payment object
    private Payment createTestPayment(String paymentId) {
        return Payment.builder()
                .paymentId(paymentId)
                .paymentCategory(Payment.paymentCategory.Consultation)
                .paymentAmount(1000)
                .paymentType(Payment.PaymentType.Card)
                .cardholderName("John Doe")
                .cardNumber("1234567812345678")
                .expiryDate("12/24")
                .cvv("123")
                .remarks("Initial remarks")
                .status("progress")
                .build();
    }

    // Helper method to create a test PaymentRequest object
    private PaymentRequest createTestPaymentRequest(String remarks, String status) {
        return PaymentRequest.builder()
                .paymentCategory(Payment.paymentCategory.Consultation)
                .paymentAmount(1000)
                .paymentType(Payment.PaymentType.Card)
                .cardholderName("John Doe")
                .cardNumber("1234567812345678")
                .expiryDate("12/24")
                .cvv("123")
                .remarks(remarks)
                .status(status)
                .build();
    }
}
