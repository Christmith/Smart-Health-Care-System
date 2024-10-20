package com.smart_healthcare_system.backend.kalindu_dev.repository;

import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepo extends MongoRepository<Appointment, String> {


    List<Appointment> findByPatientId(ObjectId patientId);

}
