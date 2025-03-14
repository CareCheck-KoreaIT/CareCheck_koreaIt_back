package com.korit.carecheckkoreait.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.korit.carecheckkoreait.mapper.SettingMapper;

@Repository
public class SettingRepository {
    @Autowired
    private SettingMapper scorePayMapper;

    public void updateScorePay(double scorePay) {
        scorePayMapper.updateScoreById(scorePay);
    };
}
