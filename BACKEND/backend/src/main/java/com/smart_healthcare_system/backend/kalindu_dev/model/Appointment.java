package com.smart_healthcare_system.backend.kalindu_dev.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String appointmentId;
    @NonNull
    private String patientId;
    @NonNull
    private String serviceId;
    @NonNull
    private String doctorId;
    @NonNull
    private Date appointmentDateTime;
    private String appointmentDesc;
    private status appointmentStatus;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    public enum status{
        pending,
        payed,
        done,
        rejected
    }
}
