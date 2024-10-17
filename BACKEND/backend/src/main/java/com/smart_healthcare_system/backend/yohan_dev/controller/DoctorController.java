package com.smart_healthcare_system.backend.yohan_dev.controller;

import com.smart_healthcare_system.backend.yohan_dev.dto.DoctorReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.DoctorRes;
import com.smart_healthcare_system.backend.yohan_dev.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Create Doctor
    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody DoctorReq doctorReq) {
        try {
            doctorService.createDoctor(doctorReq);
            return new ResponseEntity<>("Doctor created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create doctor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get All Doctors
    @GetMapping
    public ResponseEntity<List<DoctorRes>> getAllDoctors() {
        try {
            List<DoctorRes> doctors = doctorService.getAllDoctors();
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Doctors By DepartmentId
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<DoctorRes>> getDoctorsByDepartmentId(@PathVariable String departmentId) {
        try {
            List<DoctorRes> doctors = doctorService.getDoctorsByDepartmentId(departmentId);
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
