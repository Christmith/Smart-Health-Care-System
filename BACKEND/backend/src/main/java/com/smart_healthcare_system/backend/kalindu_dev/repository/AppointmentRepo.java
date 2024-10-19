package com.smart_healthcare_system.backend.kalindu_dev.repository;

import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepo extends MongoRepository<Appointment, String> { }
