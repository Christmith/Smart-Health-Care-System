package com.smart_healthcare_system.backend.yohan_dev.dto;

import com.smart_healthcare_system.backend.yohan_dev.enums.PaymentOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HospitalServiceRes {
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
}
