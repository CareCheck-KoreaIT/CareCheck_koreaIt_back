package com.korit.carecheckkoreait.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korit.carecheckkoreait.entity.Admission;

@Mapper
public interface admissionMapper {  
    int insertAdmission (Admission admission);
    List<Admission> selectWaitingList (String usercode);
    List<Admission> selectVitalInfo (int admId);
    List<Admission> selectDetailOrderByAdmId(int admId);
} 
