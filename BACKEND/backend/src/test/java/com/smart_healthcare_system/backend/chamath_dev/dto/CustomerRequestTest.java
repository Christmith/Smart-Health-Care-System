package com.smart_healthcare_system.backend.chamath_dev.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CustomerRequestTest {

//    This test ensures that a CustomerRequest object can be created using the builder pattern and that all fields are set correctly.

    @Test
    void testCustomerRequestCreationWithBuilder() {
        // Given
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
        CustomerRequest customerRequest = CustomerRequest.builder()
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
        assertEquals(fullName, customerRequest.getFullName());
        assertEquals(dateOfBirth, customerRequest.getDateOfBirth());
        assertEquals(gender, customerRequest.getGender());
        assertEquals(phoneNumber, customerRequest.getPhoneNumber());
        assertEquals(emergencyContact, customerRequest.getEmergencyContact());
        assertEquals(nicNumber, customerRequest.getNicNumber());
        assertEquals(email, customerRequest.getEmail());
        assertEquals(password, customerRequest.getPassword());
        assertEquals(medicalHistory, customerRequest.getMedicalHistory());
        assertEquals(allergies, customerRequest.getAllergies());
        assertEquals(bloodType, customerRequest.getBloodType());
        assertEquals(previousTreatments, customerRequest.getPreviousTreatments());
    }

//    This test ensures that the no-args constructor initializes a CustomerRequest object and that values can be set afterward.

    @Test
    void testCustomerRequestCreationWithNoArgsConstructor() {
        // Given
        CustomerRequest customerRequest = new CustomerRequest();

        // When
        customerRequest.setFullName("Alice Smith");
        customerRequest.setDateOfBirth("1985-05-05");
        customerRequest.setGender("Female");
        customerRequest.setPhoneNumber("0987654321");
        customerRequest.setEmergencyContact("Bob Smith");
        customerRequest.setNicNumber("NIC654321");
        customerRequest.setEmail("alice.smith@example.com");
        customerRequest.setPassword("anotherSecurePassword");
        customerRequest.setMedicalHistory(List.of("ConditionA"));
        customerRequest.setAllergies(List.of("Dust"));
        customerRequest.setBloodType("A+");
        customerRequest.setPreviousTreatments(List.of("TreatmentA"));

        // Then
        assertEquals("Alice Smith", customerRequest.getFullName());
        assertEquals("1985-05-05", customerRequest.getDateOfBirth());
        assertEquals("Female", customerRequest.getGender());
        assertEquals("0987654321", customerRequest.getPhoneNumber());
        assertEquals("Bob Smith", customerRequest.getEmergencyContact());
        assertEquals("NIC654321", customerRequest.getNicNumber());
        assertEquals("alice.smith@example.com", customerRequest.getEmail());
        assertEquals("anotherSecurePassword", customerRequest.getPassword());
        assertEquals(List.of("ConditionA"), customerRequest.getMedicalHistory());
        assertEquals(List.of("Dust"), customerRequest.getAllergies());
        assertEquals("A+", customerRequest.getBloodType());
        assertEquals(List.of("TreatmentA"), customerRequest.getPreviousTreatments());
    }

//    This test checks how the CustomerRequest class handles null values.
//    Since Lombok does not enforce validation, this test ensures that null values can be set without throwing exceptions.

    @Test
    void testCustomerRequestWithNullValues() {
        // Given
        CustomerRequest customerRequest = new CustomerRequest();

        // When
        customerRequest.setFullName(null);
        customerRequest.setDateOfBirth(null);
        customerRequest.setGender(null);
        customerRequest.setPhoneNumber(null);
        customerRequest.setEmergencyContact(null);
        customerRequest.setNicNumber(null);
        customerRequest.setEmail(null);
        customerRequest.setPassword(null);
        customerRequest.setMedicalHistory(null);
        customerRequest.setAllergies(null);
        customerRequest.setBloodType(null);
        customerRequest.setPreviousTreatments(null);

        // Then
        assertNull(customerRequest.getFullName());
        assertNull(customerRequest.getDateOfBirth());
        assertNull(customerRequest.getGender());
        assertNull(customerRequest.getPhoneNumber());
        assertNull(customerRequest.getEmergencyContact());
        assertNull(customerRequest.getNicNumber());
        assertNull(customerRequest.getEmail());
        assertNull(customerRequest.getPassword());
        assertNull(customerRequest.getMedicalHistory());
        assertNull(customerRequest.getAllergies());
        assertNull(customerRequest.getBloodType());
        assertNull(customerRequest.getPreviousTreatments());
    }

}
