package com.smart_healthcare_system.backend.yohan_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReviewReq {
    private String serviceId;
    private String customerId;
    private String customerName;
    private String review;
    private BigDecimal rating;
}
