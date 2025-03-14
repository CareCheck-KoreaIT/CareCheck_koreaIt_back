package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.CareCheckKoreaItBackApplication;
import com.korit.carecheckkoreait.dto.request.ReqAddOrderDto;
import com.korit.carecheckkoreait.dto.request.ReqSearchOrderDto;
import com.korit.carecheckkoreait.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "오더등록", description = "오더등록")
    @PostMapping("/enroll")
    public ResponseEntity<?> orderEnroll(@RequestBody ReqAddOrderDto reqAddOrderDto) {
        orderService.save(reqAddOrderDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "오더검색", description = "오더명검색")
    @GetMapping("/list")
    public ResponseEntity<?> searchOrder(@ModelAttribute ReqSearchOrderDto reqSearchOrderDto) throws Exception {
        return ResponseEntity.ok().body(orderService.getAllOrders(reqSearchOrderDto));
    }

    @Operation(summary = "점수단가 변경", description = "점수단가 변경")
    @PostMapping("/score")
    public ResponseEntity<?> updateScorePay(@RequestParam double scorePay) {
        System.out.println("controller" + scorePay);
        orderService.updateScorePay(scorePay);
        return ResponseEntity.ok().build();
    }
}
