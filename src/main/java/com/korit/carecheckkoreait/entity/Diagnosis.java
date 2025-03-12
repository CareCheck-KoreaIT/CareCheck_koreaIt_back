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
public class Diagnosis {
    private int diagnosisId;
    private int admId;
    private int diseaseId;
    private String orderCode;
    private double orderDose;
    private int orderCount;
    private int orderDays;
    private String orderMethod;
    private double orderScore;
    private double scorePay;
    private int totalPay;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
