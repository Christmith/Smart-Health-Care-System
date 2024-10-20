package com.smart_healthcare_system.backend.yohan_dev.dto;

import com.smart_healthcare_system.backend.yohan_dev.enums.PaymentOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HospitalServiceReqTest {

    private HospitalServiceReq hospitalServiceReq;

    @BeforeEach
    public void setUp() {
        hospitalServiceReq = HospitalServiceReq.builder()
                .serviceName("General Checkup")
                .serviceDescription("Routine health checkup")
                .paymentOption(Arrays.asList(PaymentOption.Cash, PaymentOption.Card))
                .department("General Medicine")
                .location("Building A")
                .status(true)
                .selectedDays(Arrays.asList("Monday", "Wednesday"))
                .selectedDoctors(Arrays.asList("Dr. Smith", "Dr. Jones"))
                .timeSlots(createTimeSlots())
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

    // Positive and Negative Test for serviceName
    @Test
    public void test_P_GetServiceName() {
        assertThat(hospitalServiceReq.getServiceName()).isEqualTo("General Checkup");
    }

    @Test
    public void test_N_GetServiceName() {
        assertThat(hospitalServiceReq.getServiceName()).isNotEqualTo("Advanced Checkup");
    }

    // Positive and Negative Test for serviceDescription
    @Test
    public void test_P_GetServiceDescription() {
        assertThat(hospitalServiceReq.getServiceDescription()).isEqualTo("Routine health checkup");
    }

    @Test
    public void test_N_GetServiceDescription() {
        assertThat(hospitalServiceReq.getServiceDescription()).isNotEqualTo("Detailed health checkup");
    }

    // Positive and Negative Test for paymentOption
    @Test
    public void test_P_GetPaymentOption() {
        List<PaymentOption> expectedOptions = Arrays.asList(PaymentOption.Cash, PaymentOption.Card);
        assertThat(hospitalServiceReq.getPaymentOption()).isEqualTo(expectedOptions);
    }

    @Test
    public void test_N_GetPaymentOption() {
        List<PaymentOption> incorrectOptions = Arrays.asList(PaymentOption.Cash, PaymentOption.Insurance);
        assertThat(hospitalServiceReq.getPaymentOption()).isNotEqualTo(incorrectOptions);
    }

    // Positive and Negative Test for department
    @Test
    public void test_P_GetDepartment() {
        assertThat(hospitalServiceReq.getDepartment()).isEqualTo("General Medicine");
    }

    @Test
    public void test_N_GetDepartment() {
        assertThat(hospitalServiceReq.getDepartment()).isNotEqualTo("Pediatrics");
    }

    // Positive and Negative Test for location
    @Test
    public void test_P_GetLocation() {
        assertThat(hospitalServiceReq.getLocation()).isEqualTo("Building A");
    }

    @Test
    public void test_N_GetLocation() {
        assertThat(hospitalServiceReq.getLocation()).isNotEqualTo("Building B");
    }

    // Positive and Negative Test for status
    @Test
    public void test_P_GetStatus() {
        assertThat(hospitalServiceReq.getStatus()).isEqualTo(true);
    }

    @Test
    public void test_N_GetStatus() {
        assertThat(hospitalServiceReq.getStatus()).isNotEqualTo(false);
    }

    // Positive and Negative Test for selectedDays
    @Test
    public void test_P_GetSelectedDays() {
        List<String> expectedDays = Arrays.asList("Monday", "Wednesday");
        assertThat(hospitalServiceReq.getSelectedDays()).isEqualTo(expectedDays);
    }

    @Test
    public void test_N_GetSelectedDays() {
        List<String> incorrectDays = Arrays.asList("Tuesday", "Thursday");
        assertThat(hospitalServiceReq.getSelectedDays()).isNotEqualTo(incorrectDays);
    }

    // Positive and Negative Test for selectedDoctors
    @Test
    public void test_P_GetSelectedDoctors() {
        List<String> expectedDoctors = Arrays.asList("Dr. Smith", "Dr. Jones");
        assertThat(hospitalServiceReq.getSelectedDoctors()).isEqualTo(expectedDoctors);
    }

    @Test
    public void test_N_GetSelectedDoctors() {
        List<String> incorrectDoctors = Arrays.asList("Dr. Brown", "Dr. White");
        assertThat(hospitalServiceReq.getSelectedDoctors()).isNotEqualTo(incorrectDoctors);
    }

    // Positive and Negative Test for timeSlots
    @Test
    public void test_P_GetTimeSlots() {
        Map<String, List<Map<String, String>>> expectedTimeSlots = createTimeSlots();
        assertThat(hospitalServiceReq.getTimeSlots()).isEqualTo(expectedTimeSlots);
    }

    @Test
    public void test_N_GetTimeSlots() {
        Map<String, List<Map<String, String>>> incorrectTimeSlots = new HashMap<>();
        incorrectTimeSlots.put("Tuesday", Arrays.asList(
                Map.of("Dr. Adams", "10:00-12:00"),
                Map.of("Dr. Brown", "15:00-17:00")));
        assertThat(hospitalServiceReq.getTimeSlots()).isNotEqualTo(incorrectTimeSlots);
    }

    // Test builder
    @Test
    public void test_P_Builder() {
        HospitalServiceReq serviceReq = HospitalServiceReq.builder()
                .serviceName("Advanced Checkup")
                .serviceDescription("Complete health checkup")
                .build();

        assertThat(serviceReq.getServiceName()).isEqualTo("Advanced Checkup");
        assertThat(serviceReq.getServiceDescription()).isEqualTo("Complete health checkup");
    }

    @Test
    public void test_N_Builder() {
        HospitalServiceReq serviceReq = HospitalServiceReq.builder()
                .serviceName("Advanced Checkup")
                .serviceDescription("Complete health checkup")
                .build();

        assertThat(serviceReq.getServiceName()).isNotEqualTo("General Checkup");
        assertThat(serviceReq.getServiceDescription()).isNotEqualTo("Routine health checkup");
    }
}
