package com.korit.carecheckkoreait.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korit.carecheckkoreait.entity.TotalSummary;

@Mapper
public interface SummaryMapper {
    List<TotalSummary> selectTotalSummaryByYear(int year);
    List<TotalSummary> selectTotalSummaryByUsercode(String usercode, int year);
}
