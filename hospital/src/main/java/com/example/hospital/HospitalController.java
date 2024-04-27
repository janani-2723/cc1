package com.example.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/hospital")
    public ResponseEntity<Hospital> add(@RequestBody Hospital hospital) {
        Hospital newHospital = hospitalService.createHospital(hospital);
        if (newHospital != null) {
            return new ResponseEntity<>(newHospital, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hospitals")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        if (!hospitals.isEmpty()) {
            return new ResponseEntity<>(hospitals, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/hospital/{hospitalId}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable int hospitalId, @RequestBody Hospital hospital) {
        boolean updated = hospitalService.updateHospital(hospitalId, hospital);
        if (updated) {
            return new ResponseEntity<>(hospital, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/hospital/{hospitalId}")
    public ResponseEntity<Boolean> deleteHospital(@PathVariable int hospitalId) {
        boolean deleted = hospitalService.deleteHospital(hospitalId);
        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}