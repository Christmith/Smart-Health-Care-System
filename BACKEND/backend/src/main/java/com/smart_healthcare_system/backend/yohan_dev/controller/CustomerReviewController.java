package com.smart_healthcare_system.backend.yohan_dev.controller;

import com.smart_healthcare_system.backend.yohan_dev.dto.CustomerReviewReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.CustomerReviewRes;
import com.smart_healthcare_system.backend.yohan_dev.service.CustomerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-reviews")
@RequiredArgsConstructor
public class CustomerReviewController {
    private final CustomerReviewService customerReviewService;

    @PostMapping
    public ResponseEntity<String> addCustomerReview(@RequestBody CustomerReviewReq customerReviewReq){
        try{
            customerReviewService.addCustomerReview(customerReviewReq);
            return new ResponseEntity<>("Customer Review Added Successfully", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to add Customer Review", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<CustomerReviewRes>> getCustomerReviewsByServiceId(@PathVariable String serviceId){
        try{
            List<CustomerReviewRes> customerReviews = customerReviewService.getCustomerReviewsByServiceID(serviceId);
            return new ResponseEntity<>(customerReviews, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
