package com.smart_healthcare_system.backend.yohan_dev.model;

import com.smart_healthcare_system.backend.yohan_dev.enums.PaymentOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(collection = "services")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HospitalService {
    @Id
    private String serviceId;
    private String serviceName;
    private String serviceDescription;
    private List<PaymentOption> paymentOption;
    private String department;
    private String location;
    private List<String> selectedDays;
    private List<String> selectedDoctors;
    private Map<String, List<Map<String, String>>> timeSlots;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
