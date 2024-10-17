package com.smart_healthcare_system.backend.yohan_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoctorReq {
    private String doctorName;
    private String doctorRegNo;
    private String doctorSpecialization;
    private String departmentId;
    private List<String> workingDays;
}
