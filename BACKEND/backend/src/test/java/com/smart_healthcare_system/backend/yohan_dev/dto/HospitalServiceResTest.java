package com.smart_healthcare_system.backend.yohan_dev.dto;

import com.smart_healthcare_system.backend.yohan_dev.enums.PaymentOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HospitalServiceResTest {

    private HospitalServiceRes hospitalServiceRes;

    @BeforeEach
    public void setUp() {
        hospitalServiceRes = HospitalServiceRes.builder()
                .serviceId("1")
                .serviceName("General Checkup")
                .serviceDescription("Routine health checkup")
                .paymentOption(Arrays.asList(PaymentOption.CASH_PAYMENT, PaymentOption.CARD_PAYMENT))
                .department("General Medicine")
                .location("Building A")
                .selectedDays(Arrays.asList("Monday", "Wednesday"))
                .selectedDoctors(Arrays.asList("Dr. Smith", "Dr. Jones"))
                .timeSlots(createTimeSlots())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    private Map<String, List<Map<String, String>>> createTimeSlots() {
        Map<String, List<Map<String, String>>> timeSlots = new HashMap<>();
        timeSlots.put("Monday", Arrays.asList(
                Map.of("Dr. Smith", "09:00-11:00"),
                Map.of("Dr. Jones", "14:00-16:00")
        ));
        timeSlots.put("Wednesday", Arrays.asList(
                Map.of("Dr. Jones", "10:00-12:00"),
                Map.of("Dr. Smith", "15:00-17:00")
        ));
        return timeSlots;
    }

    // Positive and Negative Test for serviceId
    @Test
    public void test_P_GetServiceId() {
        assertThat(hospitalServiceRes.getServiceId()).isEqualTo("1");
    }

    @Test
    public void test_N_GetServiceId() {
        assertThat(hospitalServiceRes.getServiceId()).isNotEqualTo("2");
    }

    // Positive and Negative Test for serviceName
    @Test
    public void test_P_GetServiceName() {
        assertThat(hospitalServiceRes.getServiceName()).isEqualTo("General Checkup");
    }

    @Test
    public void test_N_GetServiceName() {
        assertThat(hospitalServiceRes.getServiceName()).isNotEqualTo("Advanced Checkup");
    }

    // Positive and Negative Test for serviceDescription
    @Test
    public void test_P_GetServiceDescription() {
        assertThat(hospitalServiceRes.getServiceDescription()).isEqualTo("Routine health checkup");
    }

    @Test
    public void test_N_GetServiceDescription() {
        assertThat(hospitalServiceRes.getServiceDescription()).isNotEqualTo("Detailed health checkup");
    }

    // Positive and Negative Test for paymentOption
    @Test
    public void test_P_GetPaymentOption() {
        List<PaymentOption> expectedOptions = Arrays.asList(PaymentOption.CASH_PAYMENT, PaymentOption.CARD_PAYMENT);
        assertThat(hospitalServiceRes.getPaymentOption()).isEqualTo(expectedOptions);
    }

    @Test
    public void test_N_GetPaymentOption() {
        List<PaymentOption> incorrectOptions = Arrays.asList(PaymentOption.CASH_PAYMENT, PaymentOption.INSURANCE_PAYMENT);
        assertThat(hospitalServiceRes.getPaymentOption()).isNotEqualTo(incorrectOptions);
    }

    // Positive and Negative Test for department
    @Test
    public void test_P_GetDepartment() {
        assertThat(hospitalServiceRes.getDepartment()).isEqualTo("General Medicine");
    }

    @Test
    public void test_N_GetDepartment() {
        assertThat(hospitalServiceRes.getDepartment()).isNotEqualTo("Pediatrics");
    }

    // Positive and Negative Test for location
    @Test
    public void test_P_GetLocation() {
        assertThat(hospitalServiceRes.getLocation()).isEqualTo("Building A");
    }

    @Test
    public void test_N_GetLocation() {
        assertThat(hospitalServiceRes.getLocation()).isNotEqualTo("Building B");
    }

    // Positive and Negative Test for selectedDays
    @Test
    public void test_P_GetSelectedDays() {
        List<String> expectedDays = Arrays.asList("Monday", "Wednesday");
        assertThat(hospitalServiceRes.getSelectedDays()).isEqualTo(expectedDays);
    }

    @Test
    public void test_N_GetSelectedDays() {
        List<String> incorrectDays = Arrays.asList("Tuesday", "Thursday");
        assertThat(hospitalServiceRes.getSelectedDays()).isNotEqualTo(incorrectDays);
    }

    // Positive and Negative Test for selectedDoctors
    @Test
    public void test_P_GetSelectedDoctors() {
        List<String> expectedDoctors = Arrays.asList("Dr. Smith", "Dr. Jones");
        assertThat(hospitalServiceRes.getSelectedDoctors()).isEqualTo(expectedDoctors);
    }

    @Test
    public void test_N_GetSelectedDoctors() {
        List<String> incorrectDoctors = Arrays.asList("Dr. Brown", "Dr. White");
        assertThat(hospitalServiceRes.getSelectedDoctors()).isNotEqualTo(incorrectDoctors);
    }

    // Positive and Negative Test for timeSlots
    @Test
    public void test_P_GetTimeSlots() {
        Map<String, List<Map<String, String>>> expectedTimeSlots = createTimeSlots();
        assertThat(hospitalServiceRes.getTimeSlots()).isEqualTo(expectedTimeSlots);
    }

    @Test
    public void test_N_GetTimeSlots() {
        Map<String, List<Map<String, String>>> incorrectTimeSlots = new HashMap<>();
        incorrectTimeSlots.put("Tuesday", Arrays.asList(
                Map.of("Dr. Adams", "10:00-12:00"),
                Map.of("Dr. Smith", "15:00-17:00")));
        assertThat(hospitalServiceRes.getTimeSlots()).isNotEqualTo(incorrectTimeSlots);
    }

    // Positive and Negative Test for createdAt
    @Test
    public void test_P_GetCreatedAt() {
        Date now = new Date();
        hospitalServiceRes.setCreatedAt(now);
        assertThat(hospitalServiceRes.getCreatedAt()).isEqualTo(now);
    }

    @Test
    public void test_N_GetCreatedAt() {
        Date now = new Date();
        hospitalServiceRes.setCreatedAt(now);
        assertThat(hospitalServiceRes.getCreatedAt()).isNotEqualTo(new Date(now.getTime() - 1000));
    }

    // Positive and Negative Test for updatedAt
    @Test
    public void test_P_GetUpdatedAt() {
        Date now = new Date();
        hospitalServiceRes.setUpdatedAt(now);
        assertThat(hospitalServiceRes.getUpdatedAt()).isEqualTo(now);
    }

    @Test
    public void test_N_GetUpdatedAt() {
        Date now = new Date();
        hospitalServiceRes.setUpdatedAt(now);
        assertThat(hospitalServiceRes.getUpdatedAt()).isNotEqualTo(new Date(now.getTime() - 1000));
    }

    // Test builder
    @Test
    public void test_P_Builder() {
        HospitalServiceRes serviceRes = HospitalServiceRes.builder()
                .serviceId("2")
                .serviceName("Advanced Checkup")
                .serviceDescription("Complete health checkup")
                .build();

        assertThat(serviceRes.getServiceId()).isEqualTo("2");
        assertThat(serviceRes.getServiceName()).isEqualTo("Advanced Checkup");
        assertThat(serviceRes.getServiceDescription()).isEqualTo("Complete health checkup");
    }

    @Test
    public void test_N_Builder() {
        HospitalServiceRes serviceRes = HospitalServiceRes.builder()
                .serviceId("2")
                .serviceName("Advanced Checkup")
                .serviceDescription("Complete health checkup")
                .build();

        assertThat(serviceRes.getServiceId()).isNotEqualTo("1");
        assertThat(serviceRes.getServiceName()).isNotEqualTo("General Checkup");
        assertThat(serviceRes.getServiceDescription()).isNotEqualTo("Routine health checkup");
    }
}
