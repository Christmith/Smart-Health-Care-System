package com.smart_healthcare_system.backend.payment_tests.service;//package com.smart_healthcare_system.backend.payment_tests;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentRequest;
import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import com.smart_healthcare_system.backend.asiri_dev.repository.PaymentRepository;
import com.smart_healthcare_system.backend.asiri_dev.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceCreateTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private PaymentRequest paymentRequest;

    @BeforeEach
    public void setUp() {
        paymentRequest = PaymentRequest.builder()
                .paymentCategory(Payment.paymentCategory.valueOf("Consultation"))
                .paymentAmount(1000)
                .paymentType(Payment.PaymentType.Card)
                .cardholderName("John Doe")
                .cardNumber("1234567890123456")
                .expiryDate("12/25")
                .cvv("123")
                .build();
    }

    @Test
    public void createPayment_shouldSavePayment_withDefaultStatusAndRemarksWhenNull() {
        // When remarks and status are null
        paymentRequest.setRemarks(null);
        paymentRequest.setStatus(null);

        // Call the method under test
        paymentService.createPayment(paymentRequest);

        // Capture the payment object being saved
        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository, times(1)).save(paymentCaptor.capture());

        // Assert that default values are set
        Payment capturedPayment = paymentCaptor.getValue();
        assertEquals("", capturedPayment.getRemarks()); // Default value for remarks
        assertEquals("progress", capturedPayment.getStatus()); // Default value for status
        assertEquals(Payment.paymentCategory.Consultation, capturedPayment.getPaymentCategory());
        assertEquals(1000, capturedPayment.getPaymentAmount());
        assertEquals(Payment.PaymentType.Card, capturedPayment.getPaymentType());
    }

    @Test
    public void createPayment_shouldSavePayment_withGivenStatusAndRemarks() {
        // Set custom remarks and status
        paymentRequest.setRemarks("Test Remark");
        paymentRequest.setStatus("completed");

        // Call the method under test
        paymentService.createPayment(paymentRequest);

        // Capture the payment object being saved
        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository, times(1)).save(paymentCaptor.capture());

        // Assert that the provided values are correctly saved
        Payment capturedPayment = paymentCaptor.getValue();
        assertEquals("Test Remark", capturedPayment.getRemarks()); // Custom value for remarks
        assertEquals("completed", capturedPayment.getStatus()); // Custom value for status
        assertEquals(Payment.paymentCategory.Consultation, capturedPayment.getPaymentCategory());
        assertEquals(1000, capturedPayment.getPaymentAmount());
        assertEquals(Payment.PaymentType.Card, capturedPayment.getPaymentType());
    }


    //nagative Test case for check cardholderName is empty
    @Test
    public void createPayment_shouldThrowException_whenCardholderNameIsMissing() {
        // Set an empty cardholder name
        paymentRequest.setCardholderName("");

        // Assert that an exception is thrown when the cardholder name is missing
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.createPayment(paymentRequest);
        });

        // Verify that the repository save method is never called due to validation failure
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

}

