package com.korit.carecheckkoreait.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.korit.carecheckkoreait.entity.TotalSummary;
import com.korit.carecheckkoreait.mapper.SummaryMapper;

@Repository
public class SummaryRepository {
    @Autowired
    private SummaryMapper summaryMapper;

    public Optional<List<TotalSummary>> selectSummaryByYear(int year) {
        List<TotalSummary> summaryInfo = summaryMapper.selectTotalSummaryByYear(year);
        if (summaryInfo == null) {
            return Optional.empty();
        }
        return Optional.of(summaryInfo);
    } 

    public Optional<List<TotalSummary>> selectSummaryByUsercodeAndYear(String usercode, int year) {
        List<TotalSummary> summaryInfo = summaryMapper.selectTotalSummaryByUsercode(usercode, year);
        if (summaryInfo == null) {
            return Optional.empty();
        }
        return Optional.of(summaryInfo);
    }
}
