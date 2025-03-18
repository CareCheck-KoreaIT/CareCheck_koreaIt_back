package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqAddChartDto;
import com.korit.carecheckkoreait.dto.request.ReqSearchChartDto;
import com.korit.carecheckkoreait.entity.Chart;
import com.korit.carecheckkoreait.entity.Patient;
import com.korit.carecheckkoreait.repository.ChartRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartService {
    @Autowired
    private ChartRepository chartRepository;

    public Chart addChart(ReqAddChartDto reqAddChartDto) {

        return chartRepository.save(Chart.builder()
                .admId(reqAddChartDto.getAdmId())
                .content(reqAddChartDto.getContent())
                .build());
    }

    public List<Patient> getPatientId(ReqSearchChartDto reqSearchChartDto) throws Exception {
        return chartRepository.findByPatientId(reqSearchChartDto.getPatientId())
                .orElseThrow(() -> new NotFoundException("조회된 PatientId가 없습니다."));
    }

}
