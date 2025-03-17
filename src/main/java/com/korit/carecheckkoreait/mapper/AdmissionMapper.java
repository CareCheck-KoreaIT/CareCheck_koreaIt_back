package com.korit.carecheckkoreait.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korit.carecheckkoreait.entity.Admission;

@Mapper
public interface AdmissionMapper {
    int insertAdmission (Admission admission);
    int selectAdmissionByPatientIdAndUserCode (int patientId, String usercode);
    List<Admission> selectWaitingListByUserCode (String usercode);
    List<Admission> selectVitalInfoByAdmId (int admId);
    List<Admission> selectDetailOrderByAdmId(int admId);
}
