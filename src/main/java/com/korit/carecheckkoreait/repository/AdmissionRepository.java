package com.korit.carecheckkoreait.repository;

import com.korit.carecheckkoreait.entity.Chart;
import com.korit.carecheckkoreait.mapper.AdmissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdmissionRepository {
    @Autowired
    private AdmissionMapper admissionMapper;

    public Chart save(Chart chart) {
        admissionMapper.insertChart(chart);
        return chart;
    }
}
