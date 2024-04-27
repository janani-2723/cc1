package com.example.hospital;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepo hospitalRepo;

    public Hospital createHospital(@NonNull Hospital hospital) {
        return hospitalRepo.save(hospital);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepo.findAll();
    }

    public Hospital getHospitalById(@NonNull Integer id) {
        return hospitalRepo.findById(id).orElse(null);
    }

    public boolean updateHospital(int id, Hospital hospital) {
        if (getHospitalById(id) == null) {
            return false;
        }
        try {
            hospitalRepo.save(hospital);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteHospital(int id) {
        if (getHospitalById(id) == null) {
            return false;
        }
        hospitalRepo.deleteById(id);
        return true;
    }
}