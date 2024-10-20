package com.smart_healthcare_system.backend.chamath_dev.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CustomerResponseTest {

//    This test ensures that a CustomerResponse object can be created using the builder pattern and that all fields are set correctly.

    @Test
    void testCustomerResponseCreationWithBuilder() {
        // Given
        String customerId = "12345";
        String fullName = "Jane Doe";
        String dateOfBirth = "1992-02-02";
        String gender = "Female";
        String phoneNumber = "9876543210";
        String emergencyContact = "John Doe";
        String nicNumber = "NIC987654";
        String email = "jane.doe@example.com";
        List<String> medicalHistory = List.of("ConditionX", "ConditionY");
        List<String> allergies = List.of("Shellfish");
        String bloodType = "B+";
        List<String> previousTreatments = List.of("TreatmentX");

        // When
        CustomerResponse customerResponse = CustomerResponse.builder()
                .customerId(customerId)
                .fullName(fullName)
                .dateOfBirth(dateOfBirth)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .emergencyContact(emergencyContact)
                .nicNumber(nicNumber)
                .email(email)
                .medicalHistory(medicalHistory)
                .allergies(allergies)
                .bloodType(bloodType)
                .previousTreatments(previousTreatments)
                .build();

        // Then
        assertEquals(customerId, customerResponse.getCustomerId());
        assertEquals(fullName, customerResponse.getFullName());
        assertEquals(dateOfBirth, customerResponse.getDateOfBirth());
        assertEquals(gender, customerResponse.getGender());
        assertEquals(phoneNumber, customerResponse.getPhoneNumber());
        assertEquals(emergencyContact, customerResponse.getEmergencyContact());
        assertEquals(nicNumber, customerResponse.getNicNumber());
        assertEquals(email, customerResponse.getEmail());
        assertEquals(medicalHistory, customerResponse.getMedicalHistory());
        assertEquals(allergies, customerResponse.getAllergies());
        assertEquals(bloodType, customerResponse.getBloodType());
        assertEquals(previousTreatments, customerResponse.getPreviousTreatments());
    }

//    This test ensures that the no-args constructor initializes a CustomerResponse object and that values can be set afterward.



    @Test
    void testCustomerResponseCreationWithNoArgsConstructor() {
        // Given
        CustomerResponse customerResponse = new CustomerResponse();

        // When
        customerResponse.setCustomerId("54321");
        customerResponse.setFullName("Emily Johnson");
        customerResponse.setDateOfBirth("1988-08-08");
        customerResponse.setGender("Female");
        customerResponse.setPhoneNumber("1230984567");
        customerResponse.setEmergencyContact("Michael Johnson");
        customerResponse.setNicNumber("NIC456789");
        customerResponse.setEmail("emily.johnson@example.com");
        customerResponse.setMedicalHistory(List.of("ConditionZ"));
        customerResponse.setAllergies(List.of("Pollen"));
        customerResponse.setBloodType("A-");
        customerResponse.setPreviousTreatments(List.of("TreatmentY"));

        // Then
        assertEquals("54321", customerResponse.getCustomerId());
        assertEquals("Emily Johnson", customerResponse.getFullName());
        assertEquals("1988-08-08", customerResponse.getDateOfBirth());
        assertEquals("Female", customerResponse.getGender());
        assertEquals("1230984567", customerResponse.getPhoneNumber());
        assertEquals("Michael Johnson", customerResponse.getEmergencyContact());
        assertEquals("NIC456789", customerResponse.getNicNumber());
        assertEquals("emily.johnson@example.com", customerResponse.getEmail());
        assertEquals(List.of("ConditionZ"), customerResponse.getMedicalHistory());
        assertEquals(List.of("Pollen"), customerResponse.getAllergies());
        assertEquals("A-", customerResponse.getBloodType());
        assertEquals(List.of("TreatmentY"), customerResponse.getPreviousTreatments());
    }

//    This test checks how the CustomerResponse class handles null values.
//    Since Lombok does not enforce validation, this test ensures that null values can be set without throwing exceptions.

    @Test
    void testCustomerResponseWithNullValues() {
        // Given
        CustomerResponse customerResponse = new CustomerResponse();

        // When
        customerResponse.setCustomerId(null);
        customerResponse.setFullName(null);
        customerResponse.setDateOfBirth(null);
        customerResponse.setGender(null);
        customerResponse.setPhoneNumber(null);
        customerResponse.setEmergencyContact(null);
        customerResponse.setNicNumber(null);
        customerResponse.setEmail(null);
        customerResponse.setMedicalHistory(null);
        customerResponse.setAllergies(null);
        customerResponse.setBloodType(null);
        customerResponse.setPreviousTreatments(null);

        // Then
        assertNull(customerResponse.getCustomerId());
        assertNull(customerResponse.getFullName());
        assertNull(customerResponse.getDateOfBirth());
        assertNull(customerResponse.getGender());
        assertNull(customerResponse.getPhoneNumber());
        assertNull(customerResponse.getEmergencyContact());
        assertNull(customerResponse.getNicNumber());
        assertNull(customerResponse.getEmail());
        assertNull(customerResponse.getMedicalHistory());
        assertNull(customerResponse.getAllergies());
        assertNull(customerResponse.getBloodType());
        assertNull(customerResponse.getPreviousTreatments());
    }

}
