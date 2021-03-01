package com.example.restservice.repository;

import com.example.restservice.models.patient.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.Optional;

public interface PatientRepository extends MongoRepository<Patient, String> {
    Optional<Patient> findByUpi(String upi);
    Optional<Patient> findByPatientNameAndDateOfBirth(String patientName, Date dateOfBirth);
}
