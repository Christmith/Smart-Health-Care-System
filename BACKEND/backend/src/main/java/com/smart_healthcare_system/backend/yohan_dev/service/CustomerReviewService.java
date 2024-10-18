package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.dto.CustomerReviewReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.CustomerReviewRes;

import java.util.List;

public interface CustomerReviewService {
    void addCustomerReview(CustomerReviewReq customerReviewReq);
    List<CustomerReviewRes> getCustomerReviewsByServiceID(String serviceID);
}
