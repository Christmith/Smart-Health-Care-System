package com.smart_healthcare_system.backend.asiri_dev.repository;

import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("doctorRepositoryAsiri")
public interface DoctorRepository extends MongoRepository<Doctor, String> {
}
