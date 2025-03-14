package com.korit.carecheckkoreait.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admission {
    private int admId;
    private int patientId;
    private int usercode;
    private String clinicDeft;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String patientName;
    private List<Chart> chart;
    private List<PatientVital> vital;
    private List<Diagnosis> diagnosis;
    private List<DiagnosisOrder> diagnosisOrder;
}
