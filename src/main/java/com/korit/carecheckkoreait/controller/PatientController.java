package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqPatientRegDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PatientController {

    @Operation(summary = "회원가입(사번등록)", description = "사번등록")
    @PostMapping("/patient/registration")
    public ResponseEntity<?> registerPatient(@RequestBody ReqPatientRegDto reqPatientRegDto) {
        System.out.println(reqPatientRegDto);
        return ResponseEntity.ok().body(null);
    }
}
