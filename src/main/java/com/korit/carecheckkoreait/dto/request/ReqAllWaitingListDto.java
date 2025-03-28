package com.korit.carecheckkoreait.dto.request;

import lombok.Data;

@Data
public class ReqAllWaitingListDto {
    private int page;
    private int limitCount;
    private String keyword;
}
