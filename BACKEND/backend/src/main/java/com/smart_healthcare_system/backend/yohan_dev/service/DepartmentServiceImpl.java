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
        Department department = Department.builder()
                .departmentName(departmentReq.getDepartmentName())
                .departmentDescription(departmentReq.getDepartmentDescription())
                .build();

        departmentRepository.save(department);
        log.info("Department {} is created.", department.getDepartmentName());
    }

    @Override
    public List<DepartmentRes> getAllDepartments() {
        List <Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    private DepartmentRes mapToDepartmentResponse(Department department) {
        return DepartmentRes.builder()
                .departmentName(department.getDepartmentName())
                .departmentDescription(department.getDepartmentDescription())
                .build();
    }
}
