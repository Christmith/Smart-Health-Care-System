package com.smart_healthcare_system.backend.kalindu_dev.model;

import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
import com.smart_healthcare_system.backend.yohan_dev.model.HospitalService;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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
//    @DBRef
    @DocumentReference
    private Customer patientId;

    @NonNull    
//    @DBRef
    @DocumentReference
    private HospitalService serviceId;

    @NonNull
//    @DBRef
    @DocumentReference
    private Doctor doctorId;

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
