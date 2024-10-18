package com.smart_healthcare_system.backend.asiri_dev.repository;

import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("AppointmentRepository")
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
