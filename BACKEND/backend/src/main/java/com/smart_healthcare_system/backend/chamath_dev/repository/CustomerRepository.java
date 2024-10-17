package com.smart_healthcare_system.backend.chamath_dev.repository;

import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    // Method to find customer by email
    Optional<Customer> findByEmail(String email);
}
