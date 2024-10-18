package com.smart_healthcare_system.backend.asiri_dev.repository;

import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Customer, String> {
}
