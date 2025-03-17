package com.korit.carecheckkoreait.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeSearch {
    private int noticeId;
    private String usercode;
    private String title;
    private LocalDate createdAt;
}
