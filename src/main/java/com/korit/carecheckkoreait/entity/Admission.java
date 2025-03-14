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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Chart> chart;
    private List<Diagnosis> diagnosis;
    private List<DiagnosisOrder> diagnosisOrder;
}
