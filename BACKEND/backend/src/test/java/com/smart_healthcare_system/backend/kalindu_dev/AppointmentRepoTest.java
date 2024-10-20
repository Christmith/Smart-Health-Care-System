package com.smart_healthcare_system.backend.kalindu_dev;

import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.kalindu_dev.model.Appointment;
import com.smart_healthcare_system.backend.kalindu_dev.repository.AppointmentRepo;
import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
import com.smart_healthcare_system.backend.yohan_dev.model.HospitalService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentRepoTest {

    @Mock
    private AppointmentRepo appRepo;

    @Test
    public void AppointmentRepo_SaveAppointment_ReturnSavedAppointment() {


        Appointment mockAppointment = Appointment.builder()
                .appointmentId("67140bd7ff481446d67a16bd")
                .patientId(new Customer("671146ca4c78986afe93a886", null, null, null, null, null, null, null, null, null, null, null, null))
                .serviceId(new HospitalService("671280ee7b09d07d182006da", null, null, null, null, null, null, null, null, null, null, null))
                .doctorId(new Doctor("6710ed9565cdfb3858a5a157", null, null, null, null, null))
                .appointmentDateTime(new Date())
                .appointmentDesc("test appointment test")
                .appointmentStatus(Appointment.status.valueOf("pending"))
                .build();

        when(appRepo.save(mockAppointment)).thenReturn(mockAppointment);

        Appointment savedAppointment = appRepo.save(mockAppointment);

        assertEquals(mockAppointment, savedAppointment);
    }

}
