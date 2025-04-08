package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqAddChartDto;
import com.korit.carecheckkoreait.dto.request.ReqSearchChartDto;
import com.korit.carecheckkoreait.service.ChartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chart")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @Operation(summary = "진료대기자명단", description = "환자 차트번호로 되어 있는 대기자 명단")
    @PostMapping("/registration")
    public ResponseEntity<?> registerChart(@RequestBody ReqAddChartDto reqAddChartDto) {
        return ResponseEntity.ok(chartService.addChart(reqAddChartDto));
    }

    @Operation(summary = "환자 번호 조회", description = "환자 번호 조회")
    @GetMapping("")
    public ResponseEntity<?> searchPatient(@ModelAttribute ReqSearchChartDto reqSearchChartDto) throws Exception {
        return ResponseEntity.ok().body(chartService.getPatientId(reqSearchChartDto));
    }

}
