package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.Patient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PatientMapper {

    int insertPatient(Patient patient);
}
