package com.smart_healthcare_system.backend.chamath_dev.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {
    @Id
    private String customerId; // Unique ID for the customer
    private String fullName; // Full name of the customer
    private String dateOfBirth; // Date of birth
    private String gender; // Gender
    private String phoneNumber; // Phone number
    private String emergencyContact; // Emergency contact details
    private String nicNumber; // National ID/Passport number
    private String email; // Email address
    private String password; // Password (should be stored securely in encrypted format)
    private List<String> medicalHistory; // List of medical history records
    private List<String> allergies; // List of allergies
    private String bloodType; // Blood type
    private List<String> previousTreatments; // List of previous treatments
}
