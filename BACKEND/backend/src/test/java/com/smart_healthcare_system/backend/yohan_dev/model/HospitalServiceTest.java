package com.smart_healthcare_system.backend.yohan_dev.model;

import com.smart_healthcare_system.backend.yohan_dev.enums.PaymentOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class HospitalServiceTest {

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        // Initializing a HospitalService instance using builder
        hospitalService = HospitalService.builder()
                .serviceId("1")
                .serviceName("Cardiology Service")
                .serviceDescription("Provides heart-related services")
                .paymentOption(Arrays.asList(PaymentOption.CASH_PAYMENT, PaymentOption.CARD_PAYMENT))
                .department("Cardiology Department")
                .location("Building A")
                .selectedDays(Arrays.asList("Monday", "Wednesday", "Friday"))
                .selectedDoctors(Arrays.asList("Dr. Smith", "Dr. Adams"))
                .timeSlots(new HashMap<>())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    // Test getters
    @Test
    void testGetServiceId() {
        assertThat(hospitalService.getServiceId()).isEqualTo("1");
    }

    @Test
    void testGetServiceName() {
        assertThat(hospitalService.getServiceName()).isEqualTo("Cardiology Service");
    }

    @Test
    void testGetServiceDescription() {
        assertThat(hospitalService.getServiceDescription()).isEqualTo("Provides heart-related services");
    }

    @Test
    void testGetPaymentOption() {
        List<PaymentOption> expectedPaymentOptions = Arrays.asList(PaymentOption.CASH_PAYMENT, PaymentOption.CARD_PAYMENT);
        assertThat(hospitalService.getPaymentOption()).isEqualTo(expectedPaymentOptions);
    }

    @Test
    void testGetDepartment() {
        assertThat(hospitalService.getDepartment()).isEqualTo("Cardiology Department");
    }

    @Test
    void testGetLocation() {
        assertThat(hospitalService.getLocation()).isEqualTo("Building A");
    }

    @Test
    void testGetSelectedDays() {
        List<String> expectedDays = Arrays.asList("Monday", "Wednesday", "Friday");
        assertThat(hospitalService.getSelectedDays()).isEqualTo(expectedDays);
    }

    @Test
    void testGetSelectedDoctors() {
        List<String> expectedDoctors = Arrays.asList("Dr. Smith", "Dr. Adams");
        assertThat(hospitalService.getSelectedDoctors()).isEqualTo(expectedDoctors);
    }

    @Test
    void testGetTimeSlots() {
        Map<String, List<Map<String, String>>> expectedTimeSlots = new HashMap<>();
        hospitalService.setTimeSlots(expectedTimeSlots);
        assertThat(hospitalService.getTimeSlots()).isEqualTo(expectedTimeSlots);
    }

    // Test setters
    @Test
    void testSetServiceName() {
        hospitalService.setServiceName("Neurology Service");
        assertThat(hospitalService.getServiceName()).isEqualTo("Neurology Service");
    }

    // Test createdAt and updatedAt
    @Test
    void testCreatedAtAndUpdatedAt() {
        Date now = new Date();
        hospitalService.setCreatedAt(now);
        hospitalService.setUpdatedAt(now);

        assertThat(hospitalService.getCreatedAt()).isEqualTo(now);
        assertThat(hospitalService.getUpdatedAt()).isEqualTo(now);
    }

    // Test equals and hashCode
    @Test
    void testEqualsAndHashCode() {
        HospitalService anotherHospitalService = HospitalService.builder()
                .serviceId("1")
                .serviceName("Cardiology Service")
                .serviceDescription("Provides heart-related services")
                .paymentOption(Arrays.asList(PaymentOption.CASH_PAYMENT, PaymentOption.CARD_PAYMENT))
                .department("Cardiology Department")
                .location("Building A")
                .selectedDays(Arrays.asList("Monday", "Wednesday", "Friday"))
                .selectedDoctors(Arrays.asList("Dr. Smith", "Dr. Adams"))
                .timeSlots(new HashMap<>())
                .createdAt(hospitalService.getCreatedAt())  // Match the timestamps
                .updatedAt(hospitalService.getUpdatedAt())  // Match the timestamps
                .build();

        assertThat(hospitalService).isEqualTo(anotherHospitalService);
        assertThat(hospitalService.hashCode()).isEqualTo(anotherHospitalService.hashCode());
    }

    // Test the builder pattern
    @Test
    void testBuilder() {
        HospitalService service = HospitalService.builder()
                .serviceId("2")
                .serviceName("Orthopedic Service")
                .serviceDescription("Handles bone-related issues")
                .build();

        assertThat(service.getServiceId()).isEqualTo("2");
        assertThat(service.getServiceName()).isEqualTo("Orthopedic Service");
        assertThat(service.getServiceDescription()).isEqualTo("Handles bone-related issues");
    }
}
