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
public class Admission {
    private int admId;
    private int patientId;
    private int staffId;
    private int patientVitalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
