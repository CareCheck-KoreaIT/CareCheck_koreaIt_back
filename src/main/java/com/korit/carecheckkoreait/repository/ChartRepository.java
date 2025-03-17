package com.korit.carecheckkoreait.repository;

import com.korit.carecheckkoreait.entity.Chart;
import com.korit.carecheckkoreait.entity.Patient;
import com.korit.carecheckkoreait.mapper.ChartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChartRepository {
    @Autowired
    private ChartMapper chartMapper;

    public Chart save(Chart chart) {
        chartMapper.insertChart(chart);
        return chart;
    }

    public Optional<List<Patient>> findByPatientId(int patientId) {
        List<Patient> patients = chartMapper.selectPatientIdByChartId(patientId);
        return patients.isEmpty() ? Optional.empty() : Optional.of(patients);
    }

}
