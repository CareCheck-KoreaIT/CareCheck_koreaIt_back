package com.korit.carecheckkoreait.service;

import java.util.List;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.korit.carecheckkoreait.dto.request.ReqAddAdmissionDto;
import com.korit.carecheckkoreait.dto.request.ReqAddDiagnosisInAdmDto;
import com.korit.carecheckkoreait.dto.request.ReqAddOrderInAdmDto;
import com.korit.carecheckkoreait.dto.request.ReqAddVitalInAdmDto;
import com.korit.carecheckkoreait.entity.Admission;
import com.korit.carecheckkoreait.entity.Diagnosis;
import com.korit.carecheckkoreait.entity.DiagnosisOrder;
import com.korit.carecheckkoreait.entity.PatientVital;
import com.korit.carecheckkoreait.exception.DuplicatedValueException;
import com.korit.carecheckkoreait.exception.FieldError;
import com.korit.carecheckkoreait.repository.AdmissionRepository;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    public int duplicatedAdmission(int patientId, String usercode) {
        return admissionRepository.findAdmissionByPatientIdAndUsercode(patientId, usercode);
    }

    @Transactional(rollbackFor = Exception.class)
    public Admission insertAdmission(ReqAddAdmissionDto dto) {
        if (duplicatedAdmission(Integer.parseInt(dto.getPatientId()), dto.getUserCode())==1) {
            throw new DuplicatedValueException(List.of(FieldError.builder()
            .field("admId")
            .message("당일 접수가 된 사람입니다..")
            .build()));
        }
        Admission admission = Admission.builder()
                                .patientId(Integer.parseInt(dto.getPatientId()))
                                .clinicDeft(dto.getClinicDeft())
                                .usercode(dto.getUserCode())
                                .build();
        admissionRepository.insert(admission);
        return admission;
    }
    @Transactional(readOnly = true)
    public Admission selectPatientInfoByAdmId(int admissionId) throws Exception {
        return admissionRepository.selectPatientInfoByUserCode(admissionId)
            .orElseThrow(()-> new NotFoundException("해당 접수번호에 맞는 환자정보가 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Admission> selectWaitingListUserCode(String usercode) throws Exception {
        return admissionRepository.selectWaitingListByUserCode(usercode)
        .orElseThrow(()-> new NotFoundException("접수된 내역이 없습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertVitalInAdm(ReqAddVitalInAdmDto dto) {
        PatientVital patientVital = PatientVital.builder()
                .admId(dto.getAdmId())
                .usercode(dto.getUsercode())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .fever(dto.getFever())
                .build();
        admissionRepository.insertVitalInAdm(patientVital);
    }
    @Transactional(readOnly = true)
    public List<Admission> selectVitalByAdmId(int admissionId) throws Exception{
        return admissionRepository.selectVitalInfoByAdmId(admissionId)
        .orElseThrow(()->new NotFoundException("바이탈 체크 내역이 없습니다."));
    }

    @Transactional(readOnly = true)
    public Admission selectDetailOrderByAdmId(int admissionId) throws Exception{
        return admissionRepository.selectDetailOrderByAdmId(admissionId)
        .orElseThrow(()-> new NotFoundException("입력된 내역이 없습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertOrderInAdm(List<ReqAddOrderInAdmDto> dtoList) {
        for(ReqAddOrderInAdmDto dto : dtoList) {
            DiagnosisOrder order = DiagnosisOrder.builder()
                .admId(dto.getAdmId())
                .orderCode(dto.getOrderCode())
                .orderDose(dto.getOrderDose())
                .orderCount(dto.getOrderCount())
                .orderDays(dto.getOrderDays())
                .orderMethod(dto.getOrderMethod())
                .build();
            admissionRepository.insertOrderInAdm(order);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertDiagnosisInAdm(List<ReqAddDiagnosisInAdmDto> dtoList) {
        for (ReqAddDiagnosisInAdmDto dto : dtoList) {
            Diagnosis diagnosis = Diagnosis.builder()
                .admId(dto.getAdmId())
                .diseaseCode(dto.getDiseaseCode())
                .build();
            admissionRepository.insertDiagnosisInAdm(diagnosis);
        }
    }
    @Transactional(readOnly = true)
    public Integer selectTotalPayInAdm(int admissionId) {
        return admissionRepository.selectTotalPayInAdm(admissionId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateAdmissionStartDate(int admissionId) {
        admissionRepository.updateAdmissionStartDate(admissionId);  
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateAdmissionEndDate(int admissionId) {
        admissionRepository.updateAdmissionEndDate(admissionId);
    }
}
