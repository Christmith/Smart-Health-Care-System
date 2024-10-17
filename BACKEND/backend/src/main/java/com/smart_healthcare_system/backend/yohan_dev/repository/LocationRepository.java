package com.smart_healthcare_system.backend.yohan_dev.repository;

import com.smart_healthcare_system.backend.yohan_dev.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends MongoRepository<Location,String> {
    List<Location> findByDepartmentId(String departmentId);
}
