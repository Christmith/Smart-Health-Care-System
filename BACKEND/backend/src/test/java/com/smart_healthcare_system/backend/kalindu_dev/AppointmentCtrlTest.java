package com.smart_healthcare_system.backend.kalindu_dev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.kalindu_dev.controller.AppointmentCtrl;
import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import com.smart_healthcare_system.backend.kalindu_dev.service.AppointmentServ;
import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
import com.smart_healthcare_system.backend.yohan_dev.model.HospitalService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AppointmentCtrl.class)
public class AppointmentCtrlTest {

    private MockMvc mockMvc;

    @Mock
    private AppointmentServ appointmentServ;

    @InjectMocks
    private AppointmentCtrl appointmentCtrl;

    private Appointment mockAppointment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentCtrl).build();

        // Setting up a mock appointment
        mockAppointment = Appointment.builder()
                .appointmentId("67140bd7ff481446d67a16bd")
                .patientId(new Customer("671146ca4c78986afe93a886", null, null, null, null, null, null, null, null, null, null, null, null))
                .serviceId(new HospitalService("671280ee7b09d07d182006da", null, null, null, null, null, null, null, null, null, null, null))
                .doctorId(new Doctor("6710ed9565cdfb3858a5a157", null, null, null, null, null))
                .appointmentDateTime(new Date())
                .appointmentDesc("Test appointment description")
                .appointmentStatus(Appointment.status.valueOf("pending"))
                .build();
    }

    @Test
    public void testGetAppointments_ReturnsAppointmentList() throws Exception {
        // Given: A list of mock appointments
        List<Appointment> mockAppointments = Arrays.asList(mockAppointment);

        when(appointmentServ.getAppointments()).thenReturn(mockAppointments);

        // When and Then: Perform GET request and expect a list of appointments
        mockMvc.perform(get("/appointments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].appointmentId").value("67140bd7ff481446d67a16bd"));
    }

    @Test
    public void testGetAllCusAppointments_ReturnsAppointmentsForCustomer() throws Exception {
        // Given: A list of appointments for a customer
        when(appointmentServ.getAllCusAppointments("test@test.com")).thenReturn(List.of(mockAppointment));

        // When and Then: Perform GET request with customer email path variable
        mockMvc.perform(get("/appointments/cus/test@test.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].appointmentId").value("67140bd7ff481446d67a16bd"));
    }

    @Test
    public void testGetAppointmentById_ReturnsAppointment() throws Exception {
        // Given: A single appointment with the provided ID
        when(appointmentServ.getAppointmentById("67140bd7ff481446d67a16bd")).thenReturn(mockAppointment);

        // When and Then: Perform GET request with appointment ID path variable
        mockMvc.perform(get("/appointments/67140bd7ff481446d67a16bd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appointmentId").value("67140bd7ff481446d67a16bd"));
    }

    @Test
    public void testAddAppointment_SavesNewAppointment() throws Exception {
        // Given: Mock adding a new appointment
        when(appointmentServ.addAppointment(any(Appointment.class))).thenReturn(mockAppointment);

        // When and Then: Perform POST request to add a new appointment
        mockMvc.perform(post("/appointments/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockAppointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appointmentId").value("67140bd7ff481446d67a16bd"));
    }

    @Test
    public void testUpdateAppointment_UpdatesExistingAppointment() throws Exception {
        // Given: Mock updating an appointment
        when(appointmentServ.updateAppointment(any(Appointment.class))).thenReturn(mockAppointment);

        // When and Then: Perform PUT request to update an existing appointment
        mockMvc.perform(put("/appointments/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockAppointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appointmentId").value("67140bd7ff481446d67a16bd"));
    }

    @Test
    public void testDeleteAppointment_DeletesAppointment() throws Exception {
        // Given: Mock successful deletion
        when(appointmentServ.deleteAppointment("67140bd7ff481446d67a16bd")).thenReturn(true);

        // When and Then: Perform DELETE request to delete the appointment
        mockMvc.perform(delete("/appointments/delete/67140bd7ff481446d67a16bd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

}
