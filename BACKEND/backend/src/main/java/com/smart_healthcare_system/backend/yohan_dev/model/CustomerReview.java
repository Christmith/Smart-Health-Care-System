package com.smart_healthcare_system.backend.yohan_dev.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "customer_reviews")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReview {
    @Id
    private String reviewId;
    private String serviceId;
    private String customerId;
    private String customerName;
    private String review;
    private BigDecimal rating;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
}
