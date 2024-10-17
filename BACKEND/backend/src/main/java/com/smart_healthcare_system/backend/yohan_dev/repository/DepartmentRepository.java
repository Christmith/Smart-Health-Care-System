package com.smart_healthcare_system.backend.yohan_dev.repository;

import com.smart_healthcare_system.backend.yohan_dev.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository <Department, String>{
}
