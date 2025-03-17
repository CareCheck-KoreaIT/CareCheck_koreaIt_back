package com.korit.carecheckkoreait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korit.carecheckkoreait.dto.request.ReqAddAdmissionDto;
import com.korit.carecheckkoreait.service.AdmissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/adm")
public class AdmissionController {
    
    @Autowired
    private AdmissionService admissionService;

    @Operation(summary = "진료접수", description = "접수등록")
    @PostMapping("/insert")
    public ResponseEntity<?> insertAdm(@RequestBody ReqAddAdmissionDto dto) {
        return ResponseEntity.ok().body(admissionService.insertAdmission(dto));
    }
    @Operation(summary = "진료대기자명단", description = "직원코드로 등록된 대기자명단")
    @PostMapping("/waitingList")
    public ResponseEntity<?> selectWaitingList(
        @Parameter(description = "직원코드", example = "2025020003", required = true)
        @RequestParam String usercode) throws Exception {
            System.out.println(usercode);
        return ResponseEntity.ok().body(admissionService.selectWaitingListUserCode(usercode));
    }

    @Operation(summary = "환자바이탈정보", description = "선택환자의 바이탈 정보")
    @PostMapping("/vitalInfo")
    public ResponseEntity<?> selectVitalInfo(@RequestParam int admId ) throws Exception{
        return ResponseEntity.ok().body(admissionService.selectVitalByAdmId(admId));
    }

    @Operation(summary = "진료세부내역", description = "선택한접수번호의 세부내역")
    @PostMapping("/detailBill")
    public ResponseEntity<?> selectDetailBill(@RequestParam int admId) throws Exception{
        System.out.println(admId);
        return ResponseEntity.ok().body(admissionService.selectDetailOrderByAdmId(admId));
    } 
}
