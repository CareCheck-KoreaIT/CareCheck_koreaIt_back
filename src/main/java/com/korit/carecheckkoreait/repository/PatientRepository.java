package com.korit.carecheckkoreait.repository;

import com.korit.carecheckkoreait.entity.Patient;
import com.korit.carecheckkoreait.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepository {
    @Autowired
    public PatientMapper patientMapper;

    public Patient save(Patient patient) {
        patientMapper.insertPatient(patient);
        return patient;
    }

    public Optional<Patient> getPatientId(int patientId) {
        return Optional.ofNullable(patientMapper.selectPatientById(patientId));
    }

    public int selectPatientsCount(String patientName) {
        return patientMapper.selectPatientsCount(patientName);
    }

    public Optional<List<Patient>> selectPatientsByName(
            int startIndex,
            int limitCount,
            String patientName
    ) {
        return Optional.ofNullable(patientMapper.selectPatientsByName(startIndex, limitCount, patientName));
    }
}
