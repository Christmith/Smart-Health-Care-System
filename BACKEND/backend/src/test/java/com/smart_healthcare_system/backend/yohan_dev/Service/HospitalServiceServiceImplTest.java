package com.smart_healthcare_system.backend.yohan_dev.Service;

import com.smart_healthcare_system.backend.yohan_dev.dto.HospitalServiceReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.HospitalServiceRes;
import com.smart_healthcare_system.backend.yohan_dev.enums.PaymentOption;
import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
import com.smart_healthcare_system.backend.yohan_dev.model.HospitalService;
import com.smart_healthcare_system.backend.yohan_dev.repository.DoctorRepository;
import com.smart_healthcare_system.backend.yohan_dev.repository.HospitalServiceRepository;
import com.smart_healthcare_system.backend.yohan_dev.service.HospitalServiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HospitalServiceServiceImplTest {

    @InjectMocks
    private HospitalServiceServiceImpl hospitalServiceService;

    @Mock
    private HospitalServiceRepository hospitalServiceRepository;

    @Mock
    private DoctorRepository doctorRepository;

    private HospitalServiceReq serviceReq;

    @BeforeEach
    public void setUp() {
        serviceReq = createServiceReq();
    }

    @Test
    public void testCreateService_Success() {
        // Arrange
        Doctor doctor = new Doctor();
        doctor.setDoctorId("1");
        doctor.setDoctorName("Dr. Smith");
        doctor.setWorkingDays(new ArrayList<>());

        when(doctorRepository.findById("1")).thenReturn(Optional.of(doctor));

        // Act
        hospitalServiceService.createService(serviceReq);

        // Capture the HospitalService object being saved
        ArgumentCaptor<HospitalService> serviceCaptor = ArgumentCaptor.forClass(HospitalService.class);
        verify(hospitalServiceRepository, times(1)).save(serviceCaptor.capture());

        // Assert
        HospitalService capturedService = serviceCaptor.getValue();
        assertEquals("General Checkup", capturedService.getServiceName());
        assertTrue(doctor.getWorkingDays().contains("Monday"));
        assertTrue(doctor.getWorkingDays().contains("Wednesday"));
    }

    @Test
    public void testCreateService_DoctorNotFound() {
        // Arrange
        when(doctorRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            hospitalServiceService.createService(serviceReq);
        });
        assertThat(exception.getMessage()).isEqualTo("Doctor not found");
    }

    @Test
    public void testUpdateService_Success() {
        // Arrange
        HospitalService service = createHospitalService();
        when(hospitalServiceRepository.findById("1")).thenReturn(Optional.of(service));

        // Act
        hospitalServiceService.updateService("1", serviceReq);

        // Capture the HospitalService object being saved
        ArgumentCaptor<HospitalService> serviceCaptor = ArgumentCaptor.forClass(HospitalService.class);
        verify(hospitalServiceRepository, times(1)).save(serviceCaptor.capture());

        // Assert
        HospitalService capturedService = serviceCaptor.getValue();
        assertEquals("General Checkup", capturedService.getServiceName());
        assertTrue(capturedService.getSelectedDays().contains("Monday"));
        assertTrue(capturedService.getSelectedDays().contains("Wednesday"));
    }

    @Test
    public void testUpdateService_ServiceNotFound() {
        // Arrange
        when(hospitalServiceRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            hospitalServiceService.updateService("1", serviceReq);
        });
        assertEquals("Service not found", exception.getMessage());
    }

    @Test
    public void testGetAllServices_Success() {
        // Arrange
        List<HospitalService> services = Arrays.asList(createHospitalService(), createHospitalService());
        when(hospitalServiceRepository.findAll()).thenReturn(services);

        // Act
        List<HospitalServiceRes> result = hospitalServiceService.getAllServices();

        // Assert
        assertEquals(2, result.size());
        verify(hospitalServiceRepository, times(1)).findAll();
    }

    @Test
    public void testGetServiceById_Success() {
        // Arrange
        HospitalService service = createHospitalService();
        when(hospitalServiceRepository.findById("1")).thenReturn(Optional.of(service));

        // Act
        HospitalServiceRes result = hospitalServiceService.getServiceById("1");

        // Assert
        assertEquals("Service 1", result.getServiceName());
    }

    @Test
    public void testGetServiceById_ServiceNotFound() {
        // Arrange
        when(hospitalServiceRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            hospitalServiceService.getServiceById("1");
        });
        assertEquals("Service not found", exception.getMessage());
    }

    @Test
    public void testDeleteService_Success() {
        // Arrange
        HospitalService service = createHospitalService();
        when(hospitalServiceRepository.findById("1")).thenReturn(Optional.of(service));

        // Act
        hospitalServiceService.deleteService("1");

        // Assert
        verify(hospitalServiceRepository, times(1)).delete(service);
    }

    @Test
    public void testDeleteService_ServiceNotFound() {
        // Arrange
        when(hospitalServiceRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            hospitalServiceService.deleteService("1");
        });
        assertEquals("Service not found", exception.getMessage());
    }

    private HospitalServiceReq createServiceReq() {
        return HospitalServiceReq.builder()
                .serviceName("General Checkup")
                .serviceDescription("Routine health checkup")
                .paymentOption(Arrays.asList(PaymentOption.CASH_PAYMENT, PaymentOption.CARD_PAYMENT))
                .department("General Medicine")
                .location("Building A")
                .selectedDays(Arrays.asList("Monday", "Wednesday"))
                .selectedDoctors(Arrays.asList("1")) // Assume "1" is the ID of Dr. Smith
                .timeSlots(createTimeSlots())
                .build();
    }

    private HospitalService createHospitalService() {
        return HospitalService.builder()
                .serviceId("1")
                .serviceName("Service 1")
                .serviceDescription("Description 1")
                .paymentOption(Arrays.asList(PaymentOption.CASH_PAYMENT))
                .department("General Medicine")
                .location("Building A")
                .selectedDays(Arrays.asList("Monday"))
                .selectedDoctors(Arrays.asList("1"))
                .timeSlots(createTimeSlots())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    private Map<String, List<Map<String, String>>> createTimeSlots() {
        Map<String, List<Map<String, String>>> timeSlots = new HashMap<>();
        timeSlots.put("Monday", Arrays.asList(Map.of("1", "09:00-11:00"))); // "1" is the ID of Dr. Smith
        timeSlots.put("Wednesday", Arrays.asList(Map.of("1", "14:00-16:00")));
        return timeSlots;
    }
}
