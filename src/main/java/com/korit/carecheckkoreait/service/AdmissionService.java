package com.korit.carecheckkoreait.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.korit.carecheckkoreait.controller.AdmissionController;
import com.korit.carecheckkoreait.dto.request.ReqAddAdmissionDto;
import com.korit.carecheckkoreait.dto.request.ReqAddDiagnosisInAdmDto;
import com.korit.carecheckkoreait.dto.request.ReqAddOrderInAdmDto;
import com.korit.carecheckkoreait.entity.Admission;
import com.korit.carecheckkoreait.entity.Diagnosis;
import com.korit.carecheckkoreait.entity.DiagnosisOrder;
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
    public List<Admission> selectWaitingListUserCode(String usercode) throws Exception {
        return admissionRepository.selectWaitingListByUserCode(usercode)
        .orElseThrow(()-> new NotFoundException("접수된 내역이 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Admission> selectVitalByAdmId(int admId) throws Exception{
        return admissionRepository.selectVitalInfoByAdmId(admId)
        .orElseThrow(()->new NotFoundException("바이탈 체크 내역이 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Admission> selectDetailOrderByAdmId(int admId, String admDate) throws Exception{
        Admission admission = Admission.builder()
                                .admId(admId)
                                .admDate(admDate)
                                .build();
        return admissionRepository.selectDetailOrderByAdmId(admission)
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
}
