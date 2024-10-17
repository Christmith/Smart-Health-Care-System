package com.smart_healthcare_system.backend.yohan_dev.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document(collection = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Doctor {
    @Id
    private String doctorId;
    private String doctorName;
    private String doctorRegNo;
    private String doctorSpecialization;
    private String departmentId;
    private List<String> workingDays;
}
