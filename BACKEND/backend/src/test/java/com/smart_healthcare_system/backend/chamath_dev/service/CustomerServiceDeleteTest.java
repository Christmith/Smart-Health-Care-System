package com.smart_healthcare_system.backend.chamath_dev.service;

import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.chamath_dev.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomerServiceDeleteTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Test Case - Successful deletion
    @Test
    void testDeleteCustomer_Success() {
        // Given
        String email = "john.doe@example.com";
        Customer customer = Customer.builder()
                .email(email)
                .build();

        when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

        // When
        customerService.deleteCustomer(email);

        // Then
        verify(customerRepository, times(1)).findByEmail(email);
        verify(customerRepository, times(1)).delete(customer);
        verifyNoMoreInteractions(customerRepository);
    }

    // Negative Test Case - Customer not found
    @Test
    void testDeleteCustomer_CustomerNotFound() {
        // Given
        String email = "nonexistent@example.com";

        when(customerRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.deleteCustomer(email);
        });

        // Verify the exception message
        assertEquals("Customer not found", exception.getReason());
        verify(customerRepository, times(1)).findByEmail(email);
        verify(customerRepository, times(0)).delete(any(Customer.class)); // No delete call should be made
    }

    // Negative Test Case - Error during deletion
    @Test
    void testDeleteCustomer_DeletionError() {
        // Given
        String email = "john.doe@example.com";
        Customer customer = Customer.builder()
                .email(email)
                .build();

        when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));
        doThrow(new RuntimeException("Database error")).when(customerRepository).delete(customer);

        // When & Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.deleteCustomer(email);
        });

        // Verify the exception message
        assertEquals("Error while deleting customer", exception.getReason());
        verify(customerRepository, times(1)).findByEmail(email);
        verify(customerRepository, times(1)).delete(customer); // Delete was called but caused an error
    }
}
