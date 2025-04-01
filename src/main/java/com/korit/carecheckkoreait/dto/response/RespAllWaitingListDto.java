package com.korit.carecheckkoreait.dto.response;

import com.korit.carecheckkoreait.entity.PatientSearch;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespAllWaitingListDto {
    private int page;
    private int limitCount;
    private int totalPages;
    private int totalElements;
    private boolean isFirstPage;
    private boolean isLastPage;

    private List<PatientSearch> patientAllWaitingList;
}
