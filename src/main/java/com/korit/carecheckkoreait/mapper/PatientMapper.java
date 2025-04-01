package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.Patient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientMapper {
    int insertPatient(Patient patient);
    Patient selectPatientById(int patientId);
}
