package com.korit.carecheckkoreait.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.korit.carecheckkoreait.entity.Admission;
import com.korit.carecheckkoreait.entity.Diagnosis;
import com.korit.carecheckkoreait.entity.DiagnosisOrder;
import com.korit.carecheckkoreait.entity.PatientVital;

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
}
