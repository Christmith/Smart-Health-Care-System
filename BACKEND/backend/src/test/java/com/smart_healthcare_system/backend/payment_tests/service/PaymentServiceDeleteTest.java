package com.smart_healthcare_system.backend.payment_tests.service;

import com.smart_healthcare_system.backend.asiri_dev.repository.PaymentRepository;
import com.smart_healthcare_system.backend.asiri_dev.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PaymentServiceDeleteTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Test Case: Payment exists and should be deleted successfully
    @Test
    void testDeletePayment_PaymentExists() {
        // Arrange
        String paymentId = "12345";
        when(paymentRepository.existsById(paymentId)).thenReturn(true); // Mock the repository to say the payment exists

        // Act
        paymentService.deletePayment(paymentId);// Call the method under test

        // Assert
        verify(paymentRepository, times(1)).deleteById(paymentId);
        verify(paymentRepository, times(1)).existsById(paymentId);
    }

    // Negative Test Case: Payment does not exist, should throw ResponseStatusException
    @Test
    void testDeletePayment_PaymentDoesNotExist() {
        // Arrange
        String paymentId = "12345";
        when(paymentRepository.existsById(paymentId)).thenReturn(false);    // Mock the repository to say the payment does NOT exist

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            paymentService.deletePayment(paymentId);
        });

        // Assert exception details
        assertEquals("Payment not found", exception.getReason()); // Ensure the exception has the correct message
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());  // Ensure it's a 404 status

        verify(paymentRepository, times(1)).existsById(paymentId);   // Ensure existsById was called once
        verify(paymentRepository, never()).deleteById(paymentId); // Ensures delete is not called if payment does not exist
    }
}
