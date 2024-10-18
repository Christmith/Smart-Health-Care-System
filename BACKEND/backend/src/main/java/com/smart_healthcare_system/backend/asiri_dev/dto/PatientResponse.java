package com.smart_healthcare_system.backend.asiri_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private String patientId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String bloodType;
}
