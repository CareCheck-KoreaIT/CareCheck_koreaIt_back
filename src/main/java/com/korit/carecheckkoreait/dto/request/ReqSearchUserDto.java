package com.korit.carecheckkoreait.dto.request;

import lombok.Data;

@Data
public class ReqSearchUserDto {
    private int page;
    private int limitCount;
    private String order;
    private String searchName;
}
