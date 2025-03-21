package com.korit.carecheckkoreait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korit.carecheckkoreait.dto.request.ReqAddAdmissionDto;
import com.korit.carecheckkoreait.dto.request.ReqAddDiagnosisInAdmDto;
import com.korit.carecheckkoreait.dto.request.ReqAddOrderInAdmDto;
import com.korit.carecheckkoreait.dto.request.ReqAddVitalInAdmDto;
import com.korit.carecheckkoreait.service.AdmissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/admission")
public class AdmissionController {
    
    @Autowired
    private AdmissionService admissionService;

    @Operation(summary = "진료접수", description = "접수등록")
    @PostMapping
    public ResponseEntity<?> insertAdm(@RequestBody ReqAddAdmissionDto dto) {
        return ResponseEntity.ok().body(admissionService.insertAdmission(dto));
    }

    @Operation(summary = "접수세부정보", description = "admId를 이용, 접수된 환자정보 가져오기")
    @GetMapping("/{admissionId}")
    public ResponseEntity<?> searchPatientInfoByAdmId(@PathVariable int admissionId) throws Exception {
        return ResponseEntity.ok().body(admissionService.selectPatientInfoByAdmId(admissionId));
    }

    @Operation(summary = "진료대기자명단", description = "직원코드로 등록된 대기자명단")
    @GetMapping("/waitings")
    public ResponseEntity<?> selectWaitingList(
        @Parameter(description = "직원코드", example = "2025020003", required = true)
        @RequestParam String usercode) throws Exception {
        return ResponseEntity.ok().body(admissionService.selectWaitingListUserCode(usercode));
    }

    @Operation(summary ="환자바이탈입력", description ="해당접수번호에 등록된 바이탈 정보")
    @PostMapping("/{admissionId}/vitals")
    public ResponseEntity<?> insertVitalInfoByAdmId(@RequestBody ReqAddVitalInAdmDto dto) {
        admissionService.insertVitalInAdm(dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "환자바이탈정보", description = "선택환자의 바이탈 정보")
    @GetMapping("/{admissionId}/vitals")
    public ResponseEntity<?> selectVitalInfo(@PathVariable int admissionId ) throws Exception{
        return ResponseEntity.ok().body(admissionService.selectVitalByAdmId(admissionId));
    }

    @Operation(summary = "진료세부내역", description = "선택한접수번호의 세부내역")
    @GetMapping("/{admissionId}/billings")
    public ResponseEntity<?> selectDetailBill(
    @PathVariable int admissionId) throws Exception{
        return ResponseEntity.ok().body(admissionService.selectDetailOrderByAdmId(admissionId));
    } 

    @Operation(summary = "오더입력", description = "선택한 접수번호에 처방입력")
    @PostMapping("/{admissionId}/orders")
    public ResponseEntity<?> insertOrderInAdm(@RequestBody List<ReqAddOrderInAdmDto> dto) {
        admissionService.insertOrderInAdm(dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "진단입력", description = "선택한 접수번호에 주진단입력")
    @PostMapping("/{admissionId}/diagnosis")
    public ResponseEntity<?> insertDiagnosisInAdm(@RequestBody List<ReqAddDiagnosisInAdmDto> dto) {
        admissionService.insertDiagnosisInAdm(dto);
        return ResponseEntity.ok().build();
    } 

    @Operation(summary = "금액 조회", description = "선택한 접수번호의 총금액")
    @GetMapping("/{admissionId}/totalpay")
    public ResponseEntity<?> selectTotalPayInAdm(@PathVariable int admissionId) {
        return ResponseEntity.ok().body(admissionService.selectTotalPayInAdm(admissionId));
    }

    @Operation(summary = "진료완료", description = "선택한 접수번호의 진료완료")
    @PutMapping("/{admissionId}/complete")
    public ResponseEntity<?> updateAdmissionComplete(@PathVariable int admissionId) {
        admissionService.updateAdmissionEndDate(admissionId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "진료시작", description = "선택한 접수번호의 진료시작")
    @PutMapping("/{admissionId}/start")
    public ResponseEntity<?> updateAdmissionStart(@PathVariable int admissionId) {
        admissionService.updateAdmissionStartDate(admissionId);
        return ResponseEntity.ok().build();
    }
}
