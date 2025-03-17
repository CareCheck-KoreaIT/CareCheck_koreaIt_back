package com.korit.carecheckkoreait.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.korit.carecheckkoreait.controller.AdmissionController;
import com.korit.carecheckkoreait.entity.Admission;
import com.korit.carecheckkoreait.entity.Diagnosis;
import com.korit.carecheckkoreait.entity.DiagnosisOrder;
import com.korit.carecheckkoreait.mapper.AdmissionMapper;

@Repository
public class AdmissionRepository {

    @Autowired
    private AdmissionMapper admissionMapper;

    //환자 접수
    public Admission insert(Admission admission) {
        admissionMapper.insertAdmission(admission);
        return admission;
    }

    //환자 접수 조회
    public int findAdmissionByPatientIdAndUsercode(int patientId, String usercode) {
        return admissionMapper.selectAdmissionByPatientIdAndUserCode(patientId, usercode);
    }
    //진료대기자 명단 (usercode)별 조회
    public Optional<List<Admission>> selectWaitingListByUserCode(String usercode) {
        return admissionMapper.selectWaitingListByUserCode(usercode).isEmpty()
            ? Optional.empty()   
            : Optional.of(admissionMapper.selectWaitingListByUserCode(usercode));
    }
    //환자의 바이탈 정보 조회(admId)
    public Optional<List<Admission>> selectVitalInfoByAdmId(int admId) {
        return admissionMapper.selectVitalInfoByAdmId(admId).isEmpty()
            ? Optional.empty()
            : Optional.of(admissionMapper.selectVitalInfoByAdmId(admId));
    }
    //처방에 대한 세부내역
    public Optional<List<Admission>> selectDetailOrderByAdmId(int admId){
        return admissionMapper.selectDetailOrderByAdmId(admId).isEmpty()
            ? Optional.empty()
            : Optional.of(admissionMapper.selectDetailOrderByAdmId(admId));
    }

    public void insertOrderInAdm(DiagnosisOrder diagnosisOrders) {
        admissionMapper.insertOrderInAdmission(diagnosisOrders);
    }

    public void deleteOrderInAdm(int diagnosisOrderId) {
        admissionMapper.deleteOrderInAdmission(diagnosisOrderId);
    }

    public void insertDiagnosisInAdm(Diagnosis diagnosis) {
        admissionMapper.insertDiagnosisInAdmission(diagnosis);
    }
}


