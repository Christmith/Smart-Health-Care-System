package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.dto.DepartmentReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.DepartmentRes;

import java.util.List;

public interface DepartmentService {
    void createDepartment(DepartmentReq departmentReq);
    List<DepartmentRes> getAllDepartments();
}
