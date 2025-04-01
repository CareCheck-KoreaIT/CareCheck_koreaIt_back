package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.entity.Patient;
import com.korit.carecheckkoreait.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Transactional(rollbackFor = Exception.class)
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<Patient> getPatientId(int patientId) {
        return patientRepository.getPatientId(patientId);
    }
}
