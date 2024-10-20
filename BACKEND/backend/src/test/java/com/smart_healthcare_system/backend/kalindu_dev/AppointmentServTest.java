package com.smart_healthcare_system.backend.kalindu_dev;

import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.chamath_dev.repository.CustomerRepository;
import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import com.smart_healthcare_system.backend.kalindu_dev.repository.AppointmentRepo;
import com.smart_healthcare_system.backend.kalindu_dev.service.AppointmentServ;
import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
import com.smart_healthcare_system.backend.yohan_dev.model.HospitalService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServTest {

    @Mock
    private AppointmentRepo appointmentRepo;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AppointmentServ appointmentServ;

    Appointment mockAppointment = Appointment.builder()
            .appointmentId("67140bd7ff481446d67a16bd")
            .patientId(new Customer("671146ca4c78986afe93a886", null, null, null, null, null, null, null, null, null, null, null, null))
            .serviceId(new HospitalService("671280ee7b09d07d182006da", null, null, null, null, null, null, null, null, null, null, null))
            .doctorId(new Doctor("6710ed9565cdfb3858a5a157", null, null, null, null, null))
            .appointmentDateTime(new Date())
            .appointmentDesc("test appointment test")
            .appointmentStatus(Appointment.status.valueOf("pending"))
            .build();
    Customer mockCustomer = new Customer("671146ca4c78986afe93a886", "test@test.com", "John", "Doe", null, null, null, null, null, null, null, null, null);

    @Test
    public void AppointmentServ_GetAll_ReturnAllAppointments() {

//        List<Appointment> mockAppointments = new ArrayList<>();
//        mockAppointments.add(mockAppointment);

        when(appointmentRepo.findAll()).thenReturn(List.of(mockAppointment));

        List<Appointment> result = appointmentServ.getAppointments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockAppointment, result.get(0));

    }

    @Test
    public void testGetAllCusAppointments_ReturnsAppointmentsForCustomer() {
        // Given: Mock customer is found and has appointments
        when(customerRepository.findByEmail("test@test.com")).thenReturn(Optional.of(mockCustomer));
        when(appointmentRepo.findByPatientId(new ObjectId("671146ca4c78986afe93a886"))).thenReturn(List.of(mockAppointment));

        // When: Call the service method
        List<Appointment> result = appointmentServ.getAllCusAppointments("test@test.com");

        // Then: Assert the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockAppointment, result.get(0));
    }

    @Test
    public void testGetAllCusAppointments_ThrowsNotFoundException() {
        // Given: Customer not found
        when(customerRepository.findByEmail("nonexistent@test.com")).thenReturn(Optional.empty());

        // When: Call the service method
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                appointmentServ.getAllCusAppointments("nonexistent@test.com"));

        // Then: Assert the exception
        assertEquals("404 NOT_FOUND \"Appointment not found\"", exception.getMessage());
    }

    @Test
    public void testGetAppointmentById_ReturnsAppointment() {
        // Given: Appointment is found
        when(appointmentRepo.findById("67140bd7ff481446d67a16bd")).thenReturn(Optional.of(mockAppointment));

        // When: Call the service method
        Appointment result = appointmentServ.getAppointmentById("67140bd7ff481446d67a16bd");

        // Then: Assert the result
        assertNotNull(result);
        assertEquals(mockAppointment, result);
    }

    @Test
    public void testGetAppointmentById_ThrowsNotFoundException() {
        // Given: Appointment not found
        when(appointmentRepo.findById("nonexistent")).thenReturn(Optional.empty());

        // When: Call the service method
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                appointmentServ.getAppointmentById("nonexistent"));

        // Then: Assert the exception
        assertEquals("404 NOT_FOUND \"Appointment not found\"", exception.getMessage());
    }

    @Test
    public void testAddAppointment_SavesAppointment() {
        // Given: A new appointment to be added
        when(appointmentRepo.save(mockAppointment)).thenReturn(mockAppointment);

        // When: Call the service method
        Appointment result = appointmentServ.addAppointment(mockAppointment);

        // Then: Assert the result
        assertNotNull(result);
        assertEquals(mockAppointment, result);
    }

    @Test
    public void testUpdateAppointment_SavesUpdatedAppointment() {
        // Given: An existing appointment to be updated
        when(appointmentRepo.save(mockAppointment)).thenReturn(mockAppointment);

        // When: Call the service method
        Appointment result = appointmentServ.updateAppointment(mockAppointment);

        // Then: Assert the result
        assertNotNull(result);
        assertEquals(mockAppointment, result);
    }

    @Test
    public void testDeleteAppointment_ReturnsTrueOnSuccess() {
        // When: Call the service method
        doNothing().when(appointmentRepo).deleteById("67140bd7ff481446d67a16bd");

        // When: Call the service method
        boolean result = appointmentServ.deleteAppointment("67140bd7ff481446d67a16bd");

        // Then: Assert the result
        assertTrue(result);
    }

    @Test
    public void testDeleteAppointment_ThrowsExceptionOnFailure() {
        // Given: Simulate an exception when deleting
        doThrow(new RuntimeException()).when(appointmentRepo).deleteById("nonexistent");

        // When: Call the service method
        assertThrows(RuntimeException.class, () ->
                appointmentServ.deleteAppointment("nonexistent"));
    }

}
