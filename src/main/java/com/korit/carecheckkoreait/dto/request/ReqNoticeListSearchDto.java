package com.korit.carecheckkoreait.dto.request;

import lombok.Data;

@Data
public class ReqNoticeListSearchDto {
    private int page;
    private int limitCount;
    private String order;
    private String searchText;
}
