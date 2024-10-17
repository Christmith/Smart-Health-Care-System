package com.smart_healthcare_system.backend.yohan_dev.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "locations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Location {
    @Id
    private String locationId;
    private String locationName;
    private String locationDescription;
    private String departmentId;
    private List<String> bookedDays;
}
