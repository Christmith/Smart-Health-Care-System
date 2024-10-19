package com.smart_healthcare_system.backend.asiri_dev.service;

import com.smart_healthcare_system.backend.asiri_dev.dto.AppointmentResponse;
import com.smart_healthcare_system.backend.asiri_dev.dto.DoctorResponse;
import com.smart_healthcare_system.backend.asiri_dev.dto.PatientResponse;
import com.smart_healthcare_system.backend.asiri_dev.repository.AppointmentRepository;
import com.smart_healthcare_system.backend.asiri_dev.repository.DoctorRepository;
import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.chamath_dev.repository.CustomerRepository;
import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository patientRepository;
    private final DoctorRepository doctorRes;

    public List<AppointmentResponse> findAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(this::mapToAppointmentResponse)
                .collect(Collectors.toList());
    }


    public AppointmentResponse mapToAppointmentResponse(Appointment appointment) {

        // Map all data into AppointmentResponse DTO
        return AppointmentResponse.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentDesc(appointment.getAppointmentDesc())
                .appointmentStatus(String.valueOf(appointment.getAppointmentStatus()))
                .appointmentDateTime(appointment.getAppointmentDateTime().toString())  // You can format this if needed
                .patient(appointment.getPatientId())
                .doctor(appointment.getDoctorId())
                .build();
    }

    public void updateAppointmentStatus(String appointmentId, String newStatus) {
        // Find the existing appointment by ID
        Appointment existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));

        // Update the status field
        existingAppointment.setAppointmentStatus(Appointment.status.valueOf(newStatus));

        // Update the updatedAt field to the current date
        existingAppointment.setUpdatedAt(new Date());

        // Save the updated appointment
        appointmentRepository.save(existingAppointment);

        log.info("Appointment status updated to: {}", newStatus);
    }

    public void deleteAppointment(String appointmentId){
        if(appointmentRepository.existsById(appointmentId)){
            appointmentRepository.deleteById(appointmentId);
            log.info("Appointment Deleted");
        }
        else{
            throw new ResponseStatusException(NOT_FOUND,"Appointment not found");
        }
    }


}
