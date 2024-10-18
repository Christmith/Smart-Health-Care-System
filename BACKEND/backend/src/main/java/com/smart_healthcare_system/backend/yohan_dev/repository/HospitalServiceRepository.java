package com.smart_healthcare_system.backend.yohan_dev.repository;

import com.smart_healthcare_system.backend.yohan_dev.model.HospitalService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalServiceRepository extends MongoRepository<HospitalService, String> {
}
