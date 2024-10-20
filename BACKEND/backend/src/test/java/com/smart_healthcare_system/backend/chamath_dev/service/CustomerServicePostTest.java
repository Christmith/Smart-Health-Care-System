package com.smart_healthcare_system.backend.chamath_dev.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerRequest;
import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.chamath_dev.repository.CustomerRepository;
import com.smart_healthcare_system.backend.chamath_dev.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;


public class CustomerServicePostTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer_Success() {
        // Given
        CustomerRequest customerRequest = CustomerRequest.builder()
                .fullName("Namal Jayasighe")
                .dateOfBirth("1990-01-01")
                .gender("Male")
                .phoneNumber("0771234567")
                .emergencyContact("0753271231")
                .nicNumber("987654321V")
                .email("namal@gmail.com")
                .password("hashed_password")
                .medicalHistory(List.of("Diabetes", "Hypertension"))
                .allergies(List.of("Peanuts"))
                .bloodType("O+")
                .previousTreatments(List.of("Surgery"))
                .build();

        // When
        customerService.createCustomer(customerRequest);

        // Then
        verify(customerRepository, times(1)).save(any(Customer.class)); // Verifies that the repository save method was called
        verifyNoMoreInteractions(customerRepository); // Verifies that there were no more interactions with the repository
    }

    @Test
    void testCreateCustomer_Exception() {
        // Given
        CustomerRequest customerRequest = CustomerRequest.builder()
                .fullName("John Doe")
                .email("john.doe@example.com")
                .build();

        doThrow(new RuntimeException("Database error")).when(customerRepository).save(any(Customer.class));

        // When & Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.createCustomer(customerRequest);
        });

        assertEquals("Error while creating customer", exception.getReason());
        verify(customerRepository, times(1)).save(any(Customer.class)); // Verify that the save method was called
    }
}
