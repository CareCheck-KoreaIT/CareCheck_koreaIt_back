package com.korit.carecheckkoreait.repository;

import com.korit.carecheckkoreait.entity.Patient;
import com.korit.carecheckkoreait.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepository {
    @Autowired
    public PatientMapper patientMapper;

    public Patient save(Patient patient) {
        patientMapper.insertPatient(patient);
        return patient;
    }
}
