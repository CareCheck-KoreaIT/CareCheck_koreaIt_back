package com.korit.carecheckkoreait.dto.response;

import com.korit.carecheckkoreait.entity.Patient;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespSearchPatientsDto {
    private int page;
    private int limitCount;
    private int totalPages;
    private int totalElements;
    private boolean isFirstPage;
    private boolean isLastPage;

    private List<Patient> patientList;
}
