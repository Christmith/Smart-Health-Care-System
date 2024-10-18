package com.smart_healthcare_system.backend.asiri_dev.dto;

import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerResponse;
import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.yohan_dev.dto.DoctorRes;
import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
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
    private Customer patient;  // Corrected to use Customer
    private Doctor doctor;    // Corrected to use Doctor
    private String appointmentDesc;
    private String appointmentStatus;
    private String appointmentDateTime;

}
