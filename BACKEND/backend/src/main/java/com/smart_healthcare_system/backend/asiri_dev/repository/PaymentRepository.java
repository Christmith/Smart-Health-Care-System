package com.smart_healthcare_system.backend.asiri_dev.repository;

import com.smart_healthcare_system.backend.asiri_dev.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    
}
