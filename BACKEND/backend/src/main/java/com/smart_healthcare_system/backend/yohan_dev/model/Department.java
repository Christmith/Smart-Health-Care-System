package com.smart_healthcare_system.backend.yohan_dev.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import org.springframework.data.annotation.Id;

@Document(collection = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Department {
    @Id
    private String departmentId;
    private String departmentName;
    private String departmentDescription;
}
