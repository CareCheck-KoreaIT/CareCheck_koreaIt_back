package com.korit.carecheckkoreait.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.korit.carecheckkoreait.entity.Admission;

@Mapper
public interface admissionMapper {  
    int insertAdmission (Admission admission);
} 
