package com.korit.carecheckkoreait.mapper;

import java.util.List;

import com.korit.carecheckkoreait.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdmissionMapper {
    int insertAdmission (Admission admission);
    int selectAdmissionByPatientIdAndUserCode (int patientId, String usercode);
    Admission selectPatientInfoByAdmId(int admissionId);
    int insertOrderInAdmission (DiagnosisOrder diagnosisOrder);
    int deleteOrderInAdmission (int diagnosisOrderId);
    int insertDiagnosisInAdmission (Diagnosis diagnosis);
    int insertVitalInAdmId(PatientVital patientVital);
    Integer selectTotalPayByAdmId(int admissionId);
    List<Admission> selectWaitingListByUserCode (String usercode);
    List<Admission> selectVitalInfoByAdmId (int admissionId);
    Admission selectDetailOrderByAdmId(int admissionId);
    int updateAdmissionStartDate(int admissionId);
    int updateAdmissionEndDate(int admissionId);
    List<PatientSearch> selectAllWaitingListAdmId (String keyword);
}
