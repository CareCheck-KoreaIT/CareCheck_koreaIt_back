package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.Chart;
import com.korit.carecheckkoreait.entity.Patient;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChartMapper {
    int insertChart(Chart chart);
    List<Patient> selectPatientIdByChartId(int patientId);
}
