package com.korit.carecheckkoreait.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korit.carecheckkoreait.entity.Disease;

@Mapper
public interface DiseaseMapper {
    int insertDisease(Disease disease);
    List<Disease> selectDiseaseByDiseaseName(String diseaseName); 
}
