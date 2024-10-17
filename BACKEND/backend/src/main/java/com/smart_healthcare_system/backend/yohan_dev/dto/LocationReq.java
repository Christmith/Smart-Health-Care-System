package com.smart_healthcare_system.backend.yohan_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LocationReq {
    private String locationName;
    private String locationDescription;
    private String departmentId;
    private List<String> bookedDays;
}
