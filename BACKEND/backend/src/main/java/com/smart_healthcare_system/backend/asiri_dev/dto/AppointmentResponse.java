package com.smart_healthcare_system.backend.asiri_dev.dto;

import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerResponse;
import com.smart_healthcare_system.backend.yohan_dev.dto.DoctorRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private String appointmentId;
    private String appointmentDesc;
    private String appointmentStatus;
    private String appointmentDateTime;
    private PatientResponse patient;  // Corrected to use PatientResponse
    private DoctorResponse doctor;    // Corrected to use DoctorResponse
}
