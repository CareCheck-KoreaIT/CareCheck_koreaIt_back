package com.korit.carecheckkoreait.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korit.carecheckkoreait.entity.Admission;
import com.korit.carecheckkoreait.entity.DiagnosisOrder;

@Mapper
public interface AdmissionMapper {  
    int insertAdmission (Admission admission);
    int selectAdmissionByPatientIdAndUserCode (int patientId, String usercode);
    int insertOrderInAdmission (DiagnosisOrder diagnosisOrder);
    int deleteOrderInAdmission (int diagnosisOrderId);
    List<Admission> selectWaitingListByUserCode (String usercode);
    List<Admission> selectVitalInfoByAdmId (int admId);
    List<Admission> selectDetailOrderByAdmId(int admId);
} 
