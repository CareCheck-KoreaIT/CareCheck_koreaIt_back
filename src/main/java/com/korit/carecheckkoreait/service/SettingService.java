package com.korit.carecheckkoreait.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korit.carecheckkoreait.repository.SettingRepository;

@Service
public class SettingService {    
    @Autowired
    private SettingRepository scoreRepository;
    
    public void updateScorePay(double scorePay) {
        scoreRepository.updateScorePay(scorePay);
    }
}
