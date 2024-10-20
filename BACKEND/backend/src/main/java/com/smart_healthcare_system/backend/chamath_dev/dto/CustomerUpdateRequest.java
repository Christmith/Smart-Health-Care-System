package com.smart_healthcare_system.backend.chamath_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerUpdateRequest {
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String emergencyContact;
    private String nicNumber;
    private List<String> medicalHistory;
    private List<String> allergies;
    private List<String> previousTreatments;
}
