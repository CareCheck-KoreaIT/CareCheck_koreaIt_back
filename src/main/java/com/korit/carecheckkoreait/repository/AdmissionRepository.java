package com.korit.carecheckkoreait.repository;

import java.util.List;
import java.util.Optional;

import com.korit.carecheckkoreait.dto.response.RespWaitingListDto;
import com.korit.carecheckkoreait.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    //접수 번호로 환자정보 가져오기
    public Optional<Admission> selectPatientInfoByUserCode(int admissionId) {
        Admission admPatientInfo = admissionMapper.selectPatientInfoByAdmId(admissionId);
        if (admPatientInfo == null) {
            return Optional.empty();
        }
        return Optional.of(admPatientInfo);
    }

    //진료대기자 명단 (usercode)별 조회
    public Optional<List<Admission>> selectWaitingListByUserCode(String usercode) {
        List<Admission> waitingList = admissionMapper.selectWaitingListByUserCode(usercode);
        return waitingList.isEmpty()
            ? Optional.empty()   
            : Optional.of(waitingList);
    }

    //환자의 바이탈 정보 조회(admId)
    public Optional<List<Admission>> selectVitalInfoByAdmId(int admId) {
        List<Admission> vitalInfo = admissionMapper.selectVitalInfoByAdmId(admId);
        return vitalInfo.isEmpty()
            ? Optional.empty()
            : Optional.of(vitalInfo);
    }

    //처방에 대한 세부내역
    public Optional<Admission> selectDetailOrderByAdmId(int admissionId){
        Admission detailBillList = admissionMapper.selectDetailOrderByAdmId(admissionId);
        System.out.println(detailBillList);
        if(detailBillList == null) {
            return Optional.empty();
        }
        return Optional.of(detailBillList);
    }

    public void insertOrderInAdm(DiagnosisOrder diagnosisOrders) {
        admissionMapper.insertOrderInAdmission(diagnosisOrders);
    }

    public void deleteOrderInAdm(int diagnosisOrderId) {
        admissionMapper.deleteOrderInAdmission(diagnosisOrderId);
    }

    public void insertVitalInAdm(PatientVital patientVital) {
        admissionMapper.insertVitalInAdmId(patientVital);
    }

    public void insertDiagnosisInAdm(Diagnosis diagnosis) {
        admissionMapper.insertDiagnosisInAdmission(diagnosis);
    }

    public Integer selectTotalPayInAdm(int admissionId) {
        return admissionMapper.selectTotalPayByAdmId(admissionId);
    }

    public void updateAdmissionStartDate(int admissionId) {
        admissionMapper.updateAdmissionStartDate(admissionId);
    }

    public void updateAdmissionEndDate(int admissionId) {
        admissionMapper.updateAdmissionEndDate(admissionId);
    }

//    전체 진료대기자 명단 조회
    public List<PatientSearch> selectAllWaitingListByAdmId(
            int startIndex,
            int limitCount,
            String keyword) {
        List<PatientSearch> foundUser = admissionMapper.selectAllWaitingListAdmId(startIndex, limitCount, keyword);
//      System.out.println("Repository : " + foundUser);
        return foundUser;
    }

    public int selectWaitingListCount(String keyword) {
        return admissionMapper.selectAllWaitingListCount(keyword);
    }

    public void deleteAllWaitingByAdmId(int admId) {
        admissionMapper.deleteAllWaitingByAdmId(admId);
    }

    public Optional<List<Admission>> selectAllAdmissionIdByPatientName (String patientName) {
        List<Admission> allAdmissionsList = admissionMapper.selectAdmissionIdByPatientName(patientName);
        if(allAdmissionsList == null) {
            return Optional.empty();
        }
        return Optional.of(allAdmissionsList);
    }
}

