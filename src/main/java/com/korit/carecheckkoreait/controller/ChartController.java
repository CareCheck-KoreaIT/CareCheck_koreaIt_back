package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqAddChartDto;
import com.korit.carecheckkoreait.dto.request.ReqSearchChartDto;
import com.korit.carecheckkoreait.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;

@RestController
public class ChartController {

    @Autowired
    private ChartService chartService;

    @PostMapping("/chart/registration")
    public ResponseEntity<?> registerChart(@RequestBody ReqAddChartDto reqAddChartDto) {
        return ResponseEntity.ok(chartService.addChart(reqAddChartDto));
    }

    @GetMapping("/chart")
    public ResponseEntity<?> searchPatient(@ModelAttribute ReqSearchChartDto reqSearchChartDto) throws Exception {
        return ResponseEntity.ok().body(chartService.getPatientId(reqSearchChartDto));
    }

}
