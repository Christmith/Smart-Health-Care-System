package com.smart_healthcare_system.backend.chamath_dev.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CustomerModelTest {

    @Test
    void testCustomerCreationWithBuilder() {
        // Given
        String customerId = "123";
        String fullName = "John Doe";
        String dateOfBirth = "1990-01-01";
        String gender = "Male";
        String phoneNumber = "1234567890";
        String emergencyContact = "Jane Doe";
        String nicNumber = "NIC123456";
        String email = "john.doe@example.com";
        String password = "securePassword";
        List<String> medicalHistory = List.of("Condition1", "Condition2");
        List<String> allergies = List.of("Peanuts");
        String bloodType = "O+";
        List<String> previousTreatments = List.of("Treatment1");

        // When
        Customer customer = Customer.builder()
                .customerId(customerId)
                .fullName(fullName)
                .dateOfBirth(dateOfBirth)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .emergencyContact(emergencyContact)
                .nicNumber(nicNumber)
                .email(email)
                .password(password)
                .medicalHistory(medicalHistory)
                .allergies(allergies)
                .bloodType(bloodType)
                .previousTreatments(previousTreatments)
                .build();

        // Then
        assertEquals(customerId, customer.getCustomerId());
        assertEquals(fullName, customer.getFullName());
        assertEquals(dateOfBirth, customer.getDateOfBirth());
        assertEquals(gender, customer.getGender());
        assertEquals(phoneNumber, customer.getPhoneNumber());
        assertEquals(emergencyContact, customer.getEmergencyContact());
        assertEquals(nicNumber, customer.getNicNumber());
        assertEquals(email, customer.getEmail());
        assertEquals(password, customer.getPassword());
        assertEquals(medicalHistory, customer.getMedicalHistory());
        assertEquals(allergies, customer.getAllergies());
        assertEquals(bloodType, customer.getBloodType());
        assertEquals(previousTreatments, customer.getPreviousTreatments());
    }

    @Test
    void testCustomerCreationWithNoArgsConstructor() {
        // Given
        Customer customer = new Customer();

        // When
        customer.setCustomerId("456");
        customer.setFullName("Alice Smith");
        customer.setDateOfBirth("1985-05-05");
        customer.setGender("Female");
        customer.setPhoneNumber("0987654321");
        customer.setEmergencyContact("Bob Smith");
        customer.setNicNumber("NIC654321");
        customer.setEmail("alice.smith@example.com");
        customer.setPassword("anotherSecurePassword");
        customer.setMedicalHistory(List.of("ConditionA"));
        customer.setAllergies(List.of("Dust"));
        customer.setBloodType("A+");
        customer.setPreviousTreatments(List.of("TreatmentA"));

        // Then
        assertEquals("456", customer.getCustomerId());
        assertEquals("Alice Smith", customer.getFullName());
        assertEquals("1985-05-05", customer.getDateOfBirth());
        assertEquals("Female", customer.getGender());
        assertEquals("0987654321", customer.getPhoneNumber());
        assertEquals("Bob Smith", customer.getEmergencyContact());
        assertEquals("NIC654321", customer.getNicNumber());
        assertEquals("alice.smith@example.com", customer.getEmail());
        assertEquals("anotherSecurePassword", customer.getPassword());
        assertEquals(List.of("ConditionA"), customer.getMedicalHistory());
        assertEquals(List.of("Dust"), customer.getAllergies());
        assertEquals("A+", customer.getBloodType());
        assertEquals(List.of("TreatmentA"), customer.getPreviousTreatments());
    }

    @Test
    void testCustomerCreationWithNullValues() {
        // Given
        Customer customer = new Customer();

        // When
        customer.setCustomerId(null);
        customer.setFullName(null);
        customer.setEmail(null);

        // Then
        assertNull(customer.getCustomerId());
        assertNull(customer.getFullName());
        assertNull(customer.getEmail());
    }


}
