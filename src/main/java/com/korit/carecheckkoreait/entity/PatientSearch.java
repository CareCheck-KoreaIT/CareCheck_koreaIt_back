package com.korit.carecheckkoreait.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PatientSearch {
    private int admId;
    private int patientId;
    private String usercode;
    private String clinicDeft;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String patientName;
    private String admDate;
    private String phoneNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}