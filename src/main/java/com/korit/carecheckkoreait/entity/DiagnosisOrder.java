package com.korit.carecheckkoreait.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisOrder {
    private int diagnosisOrderId;
    private int admId;
    private String orderCode;
    private Double orderDose;
    private int orderCount;
    private int orderDays;
    private int calculateOrderPay;
    private String orderMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}