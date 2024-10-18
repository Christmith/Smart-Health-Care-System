package com.smart_healthcare_system.backend.asiri_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private String doctorId;
    private String doctorName;
    private String doctorSpecialization;
    private String doctorRegNo;
}
