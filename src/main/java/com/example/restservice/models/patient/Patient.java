package com.example.restservice.models.patient;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patients")
public class Patient {
    private String regId;
    private String doctorId;
    private String hospitalId;
    private String upi; // Universal Patient Identifier
    private String patientName;
    private String regDatetime;
    private String dateOfBirth;
    private int age;
    private String gender;
    private String occupation;
    private String healthInsuranceNo;
    private String healthCareProvider;
    private String address;
    private String contactNo;
    private String createdOn;
}