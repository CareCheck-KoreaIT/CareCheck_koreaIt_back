package com.korit.carecheckkoreait.dto.request;

import lombok.Data;

@Data
public class ReqSearchPatientsDto {
    private int page;
    private int limitCount;
    private String searchText;
}
