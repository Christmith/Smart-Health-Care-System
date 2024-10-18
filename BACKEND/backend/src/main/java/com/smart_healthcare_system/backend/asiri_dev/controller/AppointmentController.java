package com.smart_healthcare_system.backend.asiri_dev.controller;

import com.smart_healthcare_system.backend.asiri_dev.dto.AppointmentResponse;
import com.smart_healthcare_system.backend.asiri_dev.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/getAll")
    public List<AppointmentResponse> getAllAppointments() {
        return appointmentService.findAllAppointments();
    }

    @PutMapping("/{appointmentId}/{status}")
    public ResponseEntity<String> updateAppointmentStatus(
            @PathVariable String appointmentId
          , @PathVariable String status) {

        // Call the service method to update the appointment status
        appointmentService.updateAppointmentStatus(appointmentId, status);

        return ResponseEntity.ok("Appointment status updated successfully.");
    }

}
