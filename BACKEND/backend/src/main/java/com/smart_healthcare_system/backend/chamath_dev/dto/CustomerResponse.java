package com.smart_healthcare_system.backend.chamath_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    @Id
    private String customerId;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String emergencyContact;
    private String nicNumber;
    private String email;
    private List<String> medicalHistory;
    private List<String> allergies;
    private String bloodType;
    private List<String> previousTreatments;
}
