package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqSearchPatientsDto;
import com.korit.carecheckkoreait.entity.Patient;
import com.korit.carecheckkoreait.repository.PatientRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public int selectPatientsCount(String patientName) {
        return patientRepository.selectPatientsCount(patientName);
    }

    @Transactional(readOnly = true)
    public List<Patient> selectPatientsList(ReqSearchPatientsDto reqSearchPatientsDto) throws Exception {
        int startIndex = (reqSearchPatientsDto.getPage() - 1) * reqSearchPatientsDto.getLimitCount();
        List<Patient> foundPatients = patientRepository.selectPatientsByName(
                startIndex,
                reqSearchPatientsDto.getLimitCount(),
                reqSearchPatientsDto.getSearchText()
        ).orElseThrow(() -> new NotFoundException("해당 환자를 찾을 수 없습니다."));
        return foundPatients;
    }
}
