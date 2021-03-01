package com.example.restservice.models.doctor;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "doctors")
public class Doctor {
    private String doctorId;
    private String hospitalId;
    private String doctorName;
    private String speciality;
    private String address;
    private String about;
    private String profilePicture;
    private Date createdOn;
}
