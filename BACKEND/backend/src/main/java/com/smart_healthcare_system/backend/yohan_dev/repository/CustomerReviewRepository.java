package com.smart_healthcare_system.backend.yohan_dev.repository;

import com.smart_healthcare_system.backend.yohan_dev.model.CustomerReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerReviewRepository extends MongoRepository<CustomerReview, String> {
    List<CustomerReview> findByServiceId(String serviceId);
}
