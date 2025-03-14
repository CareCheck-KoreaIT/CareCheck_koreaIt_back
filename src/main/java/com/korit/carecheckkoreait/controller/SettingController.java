package com.korit.carecheckkoreait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.korit.carecheckkoreait.service.SettingService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;
    
    @PostMapping("/scorePay")
    public ResponseEntity<?> updateScorePay(@RequestParam double scorePay) {
        settingService.updateScorePay(scorePay);
        return ResponseEntity.ok().build();
    }
        
}
