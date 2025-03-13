package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqSigninDto;
import com.korit.carecheckkoreait.dto.request.ReqSignupDto;
import com.korit.carecheckkoreait.dto.response.RespTokenDto;
import com.korit.carecheckkoreait.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "회원가입(사번등록)", description = "사번등록")
    @PostMapping("/user/auth/signup")
    public ResponseEntity<?> signup(@RequestBody ReqSignupDto dto) {
        return ResponseEntity.ok().body(userService.signup(dto));
    }

    @Operation(summary = "로그인", description = "로그인")
    @GetMapping("/user/auth/signin")
    public ResponseEntity<?> signin(@RequestBody ReqSigninDto dto) {
        RespTokenDto respTokenDto = RespTokenDto.builder()
                .type("JWT")
                .name("AccessToken")
                .token(userService.signin(dto))
                .build();

        return ResponseEntity.ok().body(respTokenDto);
    }

}
