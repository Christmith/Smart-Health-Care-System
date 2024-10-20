package com.smart_healthcare_system.backend.payment_tests.service;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentResponse;
import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import com.smart_healthcare_system.backend.asiri_dev.repository.PaymentRepository;
import com.smart_healthcare_system.backend.asiri_dev.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceGetTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    public void setUp() {
        payment1 = Payment.builder()
                .paymentId(String.valueOf(1L)) // Directly using Long here
                .paymentCategory(Payment.paymentCategory.Consultation)
                .paymentAmount(1000)
                .paymentType(Payment.PaymentType.Card)
                .cardholderName("John Doe")
                .cardNumber("1234567890123456")
                .expiryDate("12/25")
                .cvv("123")
                .remarks("Test Remark 1")
                .status("completed")
                .build();

        payment2 = Payment.builder()
                .paymentId(String.valueOf(2L)) // Directly using Long here
                .paymentCategory(Payment.paymentCategory.Medication)
                .paymentAmount(2000)
                .paymentType(Payment.PaymentType.Cash)
                .cardholderName("Jane Smith")
                .cardNumber("6543210987654321")
                .expiryDate("11/24")
                .cvv("456")
                .remarks("Test Remark 2")
                .status("progress")
                .build();
    }

    @Test
    public void findAllPayments_shouldReturnListOfPaymentResponses() {
        // Mock the paymentRepository to return a list of payments
        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment1, payment2));

        // Call the method under test
        List<PaymentResponse> paymentResponses = paymentService.findAllPayments();

        // Verify the size of the result list
        assertEquals(2, paymentResponses.size());

        // Verify the mapping of the first payment
        PaymentResponse paymentResponse1 = paymentResponses.get(0);
        assertEquals(Payment.paymentCategory.Consultation, paymentResponse1.getPaymentCategory());
        assertEquals(1000, paymentResponse1.getPaymentAmount());
        assertEquals(Payment.PaymentType.Card, paymentResponse1.getPaymentType());
        assertEquals("John Doe", paymentResponse1.getCardholderName());
        assertEquals("1234567890123456", paymentResponse1.getCardNumber());
        assertEquals("12/25", paymentResponse1.getExpiryDate());
        assertEquals("123", paymentResponse1.getCvv());
        assertEquals("Test Remark 1", paymentResponse1.getRemarks());
        assertEquals("completed", paymentResponse1.getStatus());

        // Verify the mapping of the second payment
        PaymentResponse paymentResponse2 = paymentResponses.get(1);
        assertEquals(Payment.paymentCategory.Medication, paymentResponse2.getPaymentCategory());
        assertEquals(2000, paymentResponse2.getPaymentAmount());
        assertEquals(Payment.PaymentType.Cash, paymentResponse2.getPaymentType());
        assertEquals("Jane Smith", paymentResponse2.getCardholderName());
        assertEquals("6543210987654321", paymentResponse2.getCardNumber());
        assertEquals("11/24", paymentResponse2.getExpiryDate());
        assertEquals("456", paymentResponse2.getCvv());
        assertEquals("Test Remark 2", paymentResponse2.getRemarks());
        assertEquals("progress", paymentResponse2.getStatus());
    }
}
