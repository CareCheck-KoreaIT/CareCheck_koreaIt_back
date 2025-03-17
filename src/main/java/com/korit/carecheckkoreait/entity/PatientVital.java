package com.korit.carecheckkoreait.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientVital {
    private int patientVitalId;
    private int admId;
    private int usercode;
    private Double height;
    private Double weight;
    private Double fever;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}