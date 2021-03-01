package com.example.restservice.controllers.patient;

import com.example.restservice.models.hospital.Hospital;
import com.example.restservice.models.patient.Patient;
import com.example.restservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        try {
            Patient _patient = patientRepository.save(new Patient(patient.getRegId(), patient.getDoctorId(), patient.getHospitalId(), patient.getUpi(), patient.getPatientName(), patient.getRegDatetime(), patient.getDateOfBirth(), patient.getAge(), patient.getGender(), patient.getOccupation(), patient.getHealthInsuranceNo(), patient.getHealthCareProvider(), patient.getAddress(), patient.getContactNo(), patient.getCreatedOn()));
            return new ResponseEntity<>(_patient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
