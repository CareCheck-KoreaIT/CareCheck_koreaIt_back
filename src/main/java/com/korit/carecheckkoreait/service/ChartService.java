package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqAddChartDto;
import com.korit.carecheckkoreait.entity.Chart;
import com.korit.carecheckkoreait.repository.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChartService {
    @Autowired
    private AdmissionRepository admissionRepository;

    public Chart addChart(ReqAddChartDto reqAddChartDto) {

        return admissionRepository.save(Chart.builder()
                .admId(reqAddChartDto.getAdmId())
                .content(reqAddChartDto.getContent())
                .build());
    }

}
