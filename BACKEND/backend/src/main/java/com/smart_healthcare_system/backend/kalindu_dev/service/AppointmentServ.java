package com.smart_healthcare_system.backend.kalindu_dev.service;

//import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
//import org.bson.types.ObjectId;
import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import com.smart_healthcare_system.backend.kalindu_dev.repository.AppointmentRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;

@Service
public class AppointmentServ {

    @Autowired
    private AppointmentRepo appRepo;


    public List<Appointment> getAppointments() {

        return appRepo.findAll();
    }

    public Appointment getAppointmentById(String appid) {
//        System.out.println(appid);
        return appRepo.findById(appid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
//        return appid;
    }

    public Appointment addAppointment(Appointment newAppointment) {
        return appRepo.save(newAppointment);
    }

    public Appointment updateAppointment(Appointment appointment) {

        Appointment newAppointment = new Appointment(
                appointment.getAppointmentId(),
                appointment.getPatientId(),
                appointment.getServiceId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDateTime(),
                appointment.getAppointmentDesc(),
                appointment.getAppointmentStatus(),
                appointment.getCreatedAt(),
                appointment.getUpdatedAt()
        );

        return appRepo.save(newAppointment);

    }

    public boolean deleteAppointment(String appntmntid) {

        try {
            appRepo.deleteById(appntmntid);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
