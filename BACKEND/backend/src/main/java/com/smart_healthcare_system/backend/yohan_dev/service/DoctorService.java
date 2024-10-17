package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.dto.DoctorReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.DoctorRes;

import java.util.List;

public interface DoctorService {
    void createDoctor(DoctorReq doctorReq);
    List<DoctorRes> getAllDoctors();
    List<DoctorRes> getDoctorsByDepartmentId(String departmentId);
}
