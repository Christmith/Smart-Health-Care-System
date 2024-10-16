package com.smart_healthcare_system.backend.yohan_dev.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Department {
    @Id
    private String departmentName;
    private String departmentDescription;
}
