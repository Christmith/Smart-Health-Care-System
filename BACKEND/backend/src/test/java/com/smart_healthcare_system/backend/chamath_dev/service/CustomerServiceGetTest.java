package com.smart_healthcare_system.backend.chamath_dev.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerResponse;
import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.chamath_dev.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class CustomerServiceGetTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerByEmail_Success() {
        // Given
        String email = "johndoe@example.com";
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFullName("John Doe");

        when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

        // When
        CustomerResponse customerResponse = customerService.getCustomerByEmail(email);

        // Then
        assertNotNull(customerResponse);
        assertEquals("John Doe", customerResponse.getFullName());
        verify(customerRepository, times(1)).findByEmail(email);
    }

    @Test
    void testGetCustomerByEmail_CustomerNotFound() {
        // Given
        String email = "nonexistent@example.com";

        when(customerRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.getCustomerByEmail(email);
        });

        assertEquals("Customer not found", exception.getReason());
        verify(customerRepository, times(1)).findByEmail(email);
    }
}