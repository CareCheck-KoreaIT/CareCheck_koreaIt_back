package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqAddChartDto;
import com.korit.carecheckkoreait.entity.Chart;
import com.korit.carecheckkoreait.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class AdmissionController {

    @Autowired
    private ChartService chartService;

    @PostMapping("/chart/registration")

    public ResponseEntity<?> registerChart(@RequestBody ReqAddChartDto reqAddChartDto) {
        return ResponseEntity.ok(chartService.addChart(reqAddChartDto));
    }
}
