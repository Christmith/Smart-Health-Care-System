package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.dto.DepartmentReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.DepartmentRes;
import com.smart_healthcare_system.backend.yohan_dev.model.Department;
import com.smart_healthcare_system.backend.yohan_dev.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public void createDepartment(DepartmentReq departmentReq) {
        try {
            Department department = Department.builder()
                    .departmentName(departmentReq.getDepartmentName())
                    .departmentDescription(departmentReq.getDepartmentDescription())
                    .build();

            departmentRepository.save(department);
            log.info("Department {} is created.", department.getDepartmentName());
        } catch (Exception e) {
            log.error("Error while creating department: {}", e.getMessage());
            throw new RuntimeException("Failed to create department");
        }
    }

    @Override
    public List<DepartmentRes> getAllDepartments() {
        try {
            List<Department> departments = departmentRepository.findAll();
            return departments.stream().map(this::mapToDepartmentResponse).toList();
        } catch (Exception e) {
            log.error("Error while fetching departments: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch departments");
        }
    }

    private DepartmentRes mapToDepartmentResponse(Department department) {
        return DepartmentRes.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .departmentDescription(department.getDepartmentDescription())
                .build();
    }
}
