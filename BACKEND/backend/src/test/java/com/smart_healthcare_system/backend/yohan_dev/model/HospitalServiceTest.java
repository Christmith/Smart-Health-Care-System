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

    // Positive and Negative Test for serviceId
    @Test
    void test_P_GetServiceId() {
        assertThat(hospitalService.getServiceId()).isEqualTo("1");
    }

    @Test
    void test_N_GetServiceId() {
        assertThat(hospitalService.getServiceId()).isNotEqualTo("2");
    }

    // Positive and Negative Test for serviceName
    @Test
    void test_P_GetServiceName() {
        assertThat(hospitalService.getServiceName()).isEqualTo("Cardiology Service");
    }

    @Test
    void test_N_GetServiceName() {
        assertThat(hospitalService.getServiceName()).isNotEqualTo("Neurology Service");
    }

    // Positive and Negative Test for serviceDescription
    @Test
    void test_P_GetServiceDescription() {
        assertThat(hospitalService.getServiceDescription()).isEqualTo("Provides heart-related services");
    }

    @Test
    void test_N_GetServiceDescription() {
        assertThat(hospitalService.getServiceDescription()).isNotEqualTo("Provides neurology services");
    }

    // Positive and Negative Test for paymentOption
    @Test
    void test_P_GetPaymentOption() {
        List<PaymentOption> expectedPaymentOptions = Arrays.asList(PaymentOption.CASH_PAYMENT, PaymentOption.CARD_PAYMENT);
        assertThat(hospitalService.getPaymentOption()).isEqualTo(expectedPaymentOptions);
    }

    @Test
    void test_N_GetPaymentOption() {
        List<PaymentOption> incorrectPaymentOptions = Arrays.asList(PaymentOption.CASH_PAYMENT, PaymentOption.INSURANCE_PAYMENT);
        assertThat(hospitalService.getPaymentOption()).isNotEqualTo(incorrectPaymentOptions);
    }

    // Positive and Negative Test for department
    @Test
    void test_P_GetDepartment() {
        assertThat(hospitalService.getDepartment()).isEqualTo("Cardiology Department");
    }

    @Test
    void test_N_GetDepartment() {
        assertThat(hospitalService.getDepartment()).isNotEqualTo("Neurology Department");
    }

    // Positive and Negative Test for location
    @Test
    void test_P_GetLocation() {
        assertThat(hospitalService.getLocation()).isEqualTo("Building A");
    }

    @Test
    void test_N_GetLocation() {
        assertThat(hospitalService.getLocation()).isNotEqualTo("Building B");
    }

    // Positive and Negative Test for selectedDays
    @Test
    void test_P_GetSelectedDays() {
        List<String> expectedDays = Arrays.asList("Monday", "Wednesday", "Friday");
        assertThat(hospitalService.getSelectedDays()).isEqualTo(expectedDays);
    }

    @Test
    void test_N_GetSelectedDays() {
        List<String> incorrectDays = Arrays.asList("Tuesday", "Thursday");
        assertThat(hospitalService.getSelectedDays()).isNotEqualTo(incorrectDays);
    }

    // Positive and Negative Test for selectedDoctors
    @Test
    void test_P_GetSelectedDoctors() {
        List<String> expectedDoctors = Arrays.asList("Dr. Smith", "Dr. Adams");
        assertThat(hospitalService.getSelectedDoctors()).isEqualTo(expectedDoctors);
    }

    @Test
    void test_N_GetSelectedDoctors() {
        List<String> incorrectDoctors = Arrays.asList("Dr. Brown", "Dr. White");
        assertThat(hospitalService.getSelectedDoctors()).isNotEqualTo(incorrectDoctors);
    }

    // Positive and Negative Test for timeSlots
    @Test
    void test_P_GetTimeSlots() {
        Map<String, List<Map<String, String>>> expectedTimeSlots = new HashMap<>();
        hospitalService.setTimeSlots(expectedTimeSlots);
        assertThat(hospitalService.getTimeSlots()).isEqualTo(expectedTimeSlots);
    }

    @Test
    void test_N_GetTimeSlots() {
        Map<String, List<Map<String, String>>> incorrectTimeSlots = new HashMap<>();
        incorrectTimeSlots.put("Monday", Arrays.asList(
                Map.of("Dr. Adams", "10:00-12:00"),
                Map.of("Dr. Brown", "15:00-17:00")));
        hospitalService.setTimeSlots(incorrectTimeSlots);
        assertThat(hospitalService.getTimeSlots()).isNotEqualTo(new HashMap<>());
    }

    // Positive and Negative Test for setServiceName (testing setter)
    @Test
    void test_P_SetServiceName() {
        hospitalService.setServiceName("Neurology Service");
        assertThat(hospitalService.getServiceName()).isEqualTo("Neurology Service");
    }

    @Test
    void test_N_SetServiceName() {
        hospitalService.setServiceName("Neurology Service");
        assertThat(hospitalService.getServiceName()).isNotEqualTo("Cardiology Service");
    }

    // Test createdAt and updatedAt
    @Test
    void test_P_CreatedAtAndUpdatedAt() {
        Date now = new Date();
        hospitalService.setCreatedAt(now);
        hospitalService.setUpdatedAt(now);

        assertThat(hospitalService.getCreatedAt()).isEqualTo(now);
        assertThat(hospitalService.getUpdatedAt()).isEqualTo(now);
    }

    @Test
    void test_N_CreatedAtAndUpdatedAt() {
        Date now = new Date();
        hospitalService.setCreatedAt(now);
        hospitalService.setUpdatedAt(now);

        Date differentDate = new Date(now.getTime() - 10000);
        assertThat(hospitalService.getCreatedAt()).isNotEqualTo(differentDate);
        assertThat(hospitalService.getUpdatedAt()).isNotEqualTo(differentDate);
    }

    // Positive and Negative Test for equals and hashCode
    @Test
    void test_P_EqualsAndHashCode() {
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

    @Test
    void test_N_EqualsAndHashCode() {
        HospitalService differentHospitalService = HospitalService.builder()
                .serviceId("2")
                .serviceName("Neurology Service")
                .build();

        assertThat(hospitalService).isNotEqualTo(differentHospitalService);
        assertThat(hospitalService.hashCode()).isNotEqualTo(differentHospitalService.hashCode());
    }

    // Test the builder pattern
    @Test
    void test_P_Builder() {
        HospitalService service = HospitalService.builder()
                .serviceId("2")
                .serviceName("Orthopedic Service")
                .serviceDescription("Handles bone-related issues")
                .build();

        assertThat(service.getServiceId()).isEqualTo("2");
        assertThat(service.getServiceName()).isEqualTo("Orthopedic Service");
        assertThat(service.getServiceDescription()).isEqualTo("Handles bone-related issues");
    }

    @Test
    void test_N_Builder() {
        HospitalService service = HospitalService.builder()
                .serviceId("2")
                .serviceName("Orthopedic Service")
                .serviceDescription("Handles bone-related issues")
                .build();

        assertThat(service.getServiceId()).isNotEqualTo("3");  // Negative case with incorrect serviceId
        assertThat(service.getServiceName()).isNotEqualTo("Cardiology Service");  // Negative case with incorrect serviceName
    }
}
