package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.dto.LocationReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.LocationRes;

import java.util.List;

public interface LocationService {
    void addLocation(LocationReq locationReq);
    List<LocationRes> getALlLocations();
    List<LocationRes> getLocationsByDepartmentId(String departmentId);
}
