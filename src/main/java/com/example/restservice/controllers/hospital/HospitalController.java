package com.example.restservice.controllers.hospital;


import com.example.restservice.models.hospital.Hospital;
import com.example.restservice.models.tutorial.Tutorial;
import com.example.restservice.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class HospitalController {
    @Autowired
    HospitalRepository hospitalRepository;

    @PostMapping("/hospitals")
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        try {
            Hospital _hospital = hospitalRepository.save(new Hospital(hospital.getHospitalId(), hospital.getHospitalName(), hospital.getBranchName(), hospital.getAddress(), hospital.getEmail(), hospital.getContact(), hospital.getCreatedOn()));
            return new ResponseEntity<>(_hospital, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hospitals")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        try {
            List<Hospital> hospitals = new ArrayList<Hospital>();

            //if (hospitalName == null)
                hospitalRepository.findAll().forEach(hospitals::add);
            //else
            //    hospitalRepository.findByHospitalNameContaining(hospitalName).forEach(hospitals::add);

            if (hospitals.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hospitals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hospitals/{hospitalId}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable("hospitalId") String hospitalId) {
        Optional<Hospital> hospitalData = hospitalRepository.findByHospitalId(hospitalId);
        if (hospitalData.isPresent()) {
            return new ResponseEntity<>(hospitalData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hospitals/{hospitalName}")
    public ResponseEntity<List<Hospital>> getHospitalByName(@PathVariable("hospitalName") String hospitalName) {
        try {
            List<Hospital> hospitals = new ArrayList<Hospital>();

            hospitalRepository.findByHospitalNameContaining(hospitalName).forEach(hospitals::add);

            if (hospitals.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hospitals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/hospitals/{hospitalId}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable("hospitalId") String hospitalId, @RequestBody Hospital hospital) {
        Optional<Hospital> hospitalData = hospitalRepository.findByHospitalId(hospitalId);
        if (hospitalData.isPresent()){
            Hospital _hospital = hospitalData.get();

            if (_hospital.getHospitalId() != null){
                _hospital.setHospitalId(hospital.getHospitalId());
            }
            if (_hospital.getHospitalName() != null){
                _hospital.setHospitalName(hospital.getHospitalName());
            }
            if (_hospital.getBranchName() != null){
                _hospital.setBranchName(hospital.getBranchName());
            }
            if (_hospital.getAddress() != null){
                _hospital.setAddress(hospital.getAddress());
            }
            if (_hospital.getEmail() != null){
                _hospital.setEmail(hospital.getEmail());
            }
            if (_hospital.getContact() != null){
                _hospital.setContact(hospital.getContact());
            }
            if (_hospital.getCreatedOn() != null){
                _hospital.setCreatedOn(hospital.getCreatedOn());
            }
            return new ResponseEntity<>(hospitalRepository.save(_hospital), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/hospitals/{hospitalId}")
    public ResponseEntity<HttpStatus> deleteHospital(@PathVariable("hospitalId") String hospitalId) {
        try {
            hospitalRepository.deleteById(hospitalId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/hospitals")
    public ResponseEntity<HttpStatus> deleteAllHospitals() {
        try {
            hospitalRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
