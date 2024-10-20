package com.smart_healthcare_system.backend.yohan_dev.controller;

import com.smart_healthcare_system.backend.yohan_dev.dto.DepartmentReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.DepartmentRes;
import com.smart_healthcare_system.backend.yohan_dev.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentReq departmentReq) {
        try {
            departmentService.createDepartment(departmentReq);
            return new ResponseEntity<>("Department created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create department", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<DepartmentRes>> getAllDepartments() {
        try {
            List<DepartmentRes> departments = departmentService.getAllDepartments();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
