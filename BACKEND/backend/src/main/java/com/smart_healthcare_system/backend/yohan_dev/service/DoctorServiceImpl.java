package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.dto.DoctorReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.DoctorRes;
import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
import com.smart_healthcare_system.backend.yohan_dev.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    // Create Doctor
    @Override
    public void createDoctor(DoctorReq doctorReq) {
        try {
            Doctor doctor = Doctor.builder()
                    .doctorName(doctorReq.getDoctorName())
                    .doctorRegNo(doctorReq.getDoctorRegNo())
                    .doctorSpecialization(doctorReq.getDoctorSpecialization())
                    .departmentId(doctorReq.getDepartmentId())
                    .workingDays(doctorReq.getWorkingDays())
                    .build();

            doctorRepository.save(doctor);
            log.info("Doctor {} is created.", doctor.getDoctorName());
        } catch (Exception e) {
            log.error("Error while creating doctor: {}", e.getMessage());
            throw new RuntimeException("Failed to create doctor");
        }
    }

    // Get All Doctors
    @Override
    public List<DoctorRes> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorRepository.findAll();
            return doctors.stream().map(this::mapToDoctorResponse).toList();
        } catch (Exception e) {
            log.error("Error while fetching doctors: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch doctors");
        }
    }

    // Get Doctors By DepartmentId
    @Override
    public List<DoctorRes> getDoctorsByDepartmentId(String departmentId) {
        try{
            List<Doctor> doctors = doctorRepository.findByDepartmentId(departmentId);
            return doctors.stream().map(this::mapToDoctorResponse).toList();
        } catch (Exception e) {
            log.error("Error while fetching doctors by departmentId: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch doctors by departmentId");
        }
    }

    // Map Doctor to Doctor Response
    private DoctorRes mapToDoctorResponse(Doctor doctor) {
        return DoctorRes.builder()
                .doctorId(doctor.getDoctorId())
                .doctorName(doctor.getDoctorName())
                .doctorRegNo(doctor.getDoctorRegNo())
                .doctorSpecialization(doctor.getDoctorSpecialization())
                .departmentId(doctor.getDepartmentId())
                .workingDays(doctor.getWorkingDays())
                .build();
    }
}
