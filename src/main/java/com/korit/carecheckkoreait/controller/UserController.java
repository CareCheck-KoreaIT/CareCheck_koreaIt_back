package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqChangeEmailDto;
import com.korit.carecheckkoreait.dto.request.ReqSigninDto;
import com.korit.carecheckkoreait.dto.request.ReqSignupDto;
import com.korit.carecheckkoreait.dto.response.RespTokenDto;
import com.korit.carecheckkoreait.security.principal.PrincipalUser;
import com.korit.carecheckkoreait.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "회원가입(사번등록)", description = "사번등록")
    @PostMapping("/users")
    public ResponseEntity<?> signup(@RequestBody ReqSignupDto dto) {
        return ResponseEntity.ok().body(userService.signup(dto));
    }






}