package com.korit.carecheckkoreait.controller;

import java.util.List;

import com.korit.carecheckkoreait.dto.request.*;
import com.korit.carecheckkoreait.dto.response.RespAllWaitingListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
            @AuthenticationPrincipal PrincipalUser principalUser) throws Exception {
        return ResponseEntity.ok().body(admissionService.selectWaitingListUserCode(principalUser.getUsercode()));
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

    @GetMapping("/all-waitings")
    public ResponseEntity<?> getAllWaitingList(
            @PathVariable Integer admId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limitCount) {
            System.out.println("Page: " + page);
            System.out.println("admId: " + admId);
            System.out.println("Limit Count: " + limitCount);
            System.out.println("Keyword: " + keyword);

        if (page < 1 || limitCount < 1) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        }
        int totalAllWaitingListCount = admissionService.getWaitingListCount(keyword, admId);

        int totalPages = totalAllWaitingListCount % limitCount == 0
                ? totalAllWaitingListCount / limitCount
                : totalAllWaitingListCount / limitCount + 1;

        RespAllWaitingListDto respAllWaitingListDto =
                RespAllWaitingListDto.builder()
                        .page(page)
                        .limitCount(limitCount)
                        .totalPages(totalPages)
                        .totalElements(totalAllWaitingListCount)
                        .isFirstPage(page == 1)
                        .isLastPage(page == totalPages)
                        .patientAllWaitingList(admissionService.getAllWaitingListKeyword(admId, keyword, page, limitCount))
                        .build();
        return ResponseEntity.ok().body(respAllWaitingListDto);

  //  전체 대기자 수 조회
    @Operation(summary = "전체대기자수", description = "접수된 전체 대기자 수")
    @GetMapping("/waiting-count")
    public ResponseEntity<?> getWaitingListCount(@RequestParam(value = "keyword", required = false) String keyword) {
        int count = admissionService.getWaitingListCount(keyword);
        return ResponseEntity.ok().body(count);
    }

    @Operation(summary = "접수된 전체 대기자 명단", description = "접수된 대기자 명단 삭제")
    @DeleteMapping("/{admissionId}")
    public String deletePatientByAdmId(@PathVariable int admissionId) {
        System.out.println("삭제 시행함: " + admissionId);
        admissionService.deleteAllWaitingByAdmId(admissionId);
        return "환자가 삭제되었습니다.";
    }

    @Operation(summary = "환자이름 기준 접수 명단", description = "해당환자의 접수 내역")
    @GetMapping("/admission-list")
    public ResponseEntity<?> getAdmissionListByPatientName(
        @Parameter(description = "환자명", example = "거북이", required = true)
        @RequestParam String patientName
    ) throws Exception {
        return ResponseEntity.ok().body(admissionService.getAllAdmissionListByPatientName(patientName));
    }
}
