package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemSettingController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "점수단가 변경", description = "점수단가 변경")
    @PostMapping("/orders/score")
    public ResponseEntity<?> updateScorePay(@RequestParam double scorePay) {
        System.out.println("controller" + scorePay);
        orderService.updateScorePay(scorePay);
        return ResponseEntity.ok().build();
    }
}
