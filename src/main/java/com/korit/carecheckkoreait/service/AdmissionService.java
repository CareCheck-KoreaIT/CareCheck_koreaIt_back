package com.korit.carecheckkoreait.service;

import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.korit.carecheckkoreait.dto.request.ReqAddAdmissionDto;
import com.korit.carecheckkoreait.entity.Admission;
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
        System.out.println("service"+dto);
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
    public List<Admission> selectDetailOrderByAdmId(int admId) throws Exception{
        return admissionRepository.selectDetailOrderByAdmId(admId)
        .orElseThrow(()-> new NotFoundException("입력된 내역이 없습니다."));
    }
}
