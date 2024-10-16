package com.smart_healthcare_system.backend.yohan_dev.controller;

import com.smart_healthcare_system.backend.yohan_dev.dto.DepartmentReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.DepartmentRes;
import com.smart_healthcare_system.backend.yohan_dev.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDepartment(@RequestBody DepartmentReq departmentReq) {
        departmentService.createDepartment(departmentReq);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentRes> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

}




