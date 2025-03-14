package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqPatientRegDto;
import com.korit.carecheckkoreait.entity.Patient;
import com.korit.carecheckkoreait.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Operation(summary = "환자등록추가", description = "환자등록")
    @PostMapping("/patient/registration")
    public ResponseEntity<?> registerPatient(@RequestBody ReqPatientRegDto reqPatientRegDto) {
        Patient patient = Patient.builder()
                .patientName(reqPatientRegDto.getPatientName())
                .regidentNum(reqPatientRegDto.getRegidentNum())
                .phoneNum(reqPatientRegDto.getPhoneNumber())
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(patientService.addPatient(patient));
    }
}
