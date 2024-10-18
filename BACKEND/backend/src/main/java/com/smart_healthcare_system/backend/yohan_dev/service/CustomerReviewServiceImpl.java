package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.dto.CustomerReviewReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.CustomerReviewRes;
import com.smart_healthcare_system.backend.yohan_dev.model.CustomerReview;
import com.smart_healthcare_system.backend.yohan_dev.repository.CustomerReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerReviewServiceImpl implements CustomerReviewService {

    private final CustomerReviewRepository customerReviewRepository;

    // Add a customer review
    @Override
    public void addCustomerReview(CustomerReviewReq customerReviewReq) {
        try{
            CustomerReview customerReview = CustomerReview.builder()
                    .serviceId(customerReviewReq.getServiceId())
                    .customerId(customerReviewReq.getCustomerId())
                    .customerName(customerReviewReq.getCustomerName())
                    .review(customerReviewReq.getReview())
                    .rating(customerReviewReq.getRating())
                    .build();
            customerReviewRepository.save(customerReview);
            log.info("{}'s review is added for service {}", customerReview.getCustomerName(), customerReview.getServiceId());
        }
        catch (Exception e){
            log.error("Error while adding review: {}", e.getMessage());
            throw new RuntimeException("Failed to add review");
        }
    }

    // Get all customer reviews for a service
    @Override
    public List<CustomerReviewRes> getCustomerReviewsByServiceID(String serviceID) {
        try{
            List<CustomerReview> customerReviews = customerReviewRepository.findByServiceId(serviceID);
            return customerReviews.stream().map(this::mapToCustomerReviewResponse).toList();
        }
        catch (Exception e){
            log.error("Error while fetching reviews: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch reviews");
        }
    }

    // Map CustomerReview to CustomerReviewRes
    private CustomerReviewRes mapToCustomerReviewResponse(CustomerReview customerReview){
        return CustomerReviewRes.builder()
                .reviewId(customerReview.getReviewId())
                .serviceId(customerReview.getServiceId())
                .customerId(customerReview.getCustomerId())
                .customerName(customerReview.getCustomerName())
                .review(customerReview.getReview())
                .rating(customerReview.getRating())
                .createdAt(customerReview.getCreatedAt())
                .updatedAt(customerReview.getUpdatedAt())
                .build();
    }
}
