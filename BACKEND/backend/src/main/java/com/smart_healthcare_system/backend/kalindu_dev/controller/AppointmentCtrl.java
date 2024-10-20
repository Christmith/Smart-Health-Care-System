package com.smart_healthcare_system.backend.kalindu_dev.controller;

//import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import com.smart_healthcare_system.backend.kalindu_dev.service.AppointmentServ;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/appointments")
public class AppointmentCtrl {

    @Autowired
    private AppointmentServ appointmentService;

    @GetMapping
    public List<Appointment> getAppointments() {
        return appointmentService.getAppointments();
    }

    @GetMapping("/{appid}")
    public Appointment getAppointmentById(@PathVariable String appid) {
        return appointmentService.getAppointmentById(appid);
    }

    @PostMapping("/add")
    public Appointment addAppointment(@RequestBody Appointment newAppointment) {
        return appointmentService.addAppointment(newAppointment);
    }

    @PutMapping("/update")
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping("/delete/{appntmntid}")
    public boolean deleteAppointment(@PathVariable String appntmntid) {
        return appointmentService.deleteAppointment(appntmntid);
    }

}
