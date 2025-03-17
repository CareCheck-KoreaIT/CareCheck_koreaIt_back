package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqChangeEmailDto;
import com.korit.carecheckkoreait.dto.request.ReqSigninDto;
import com.korit.carecheckkoreait.dto.request.ReqSignupDto;
import com.korit.carecheckkoreait.dto.response.RespTokenDto;
import com.korit.carecheckkoreait.security.principal.PrincipalUser;
import com.korit.carecheckkoreait.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "회원가입(사번등록)", description = "사번등록")
    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody ReqSignupDto dto) {
        return ResponseEntity.ok().body(userService.signup(dto));
    }

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@RequestBody ReqSigninDto dto) {
        RespTokenDto respTokenDto = RespTokenDto.builder()
                .type("JWT")
                .name("AccessToken")
                .token(userService.signin(dto))
                .build();

        return ResponseEntity.ok().body(respTokenDto);
    }

    @PutMapping("/changeInfo/password")
    @Operation(summary = "비밀번호 변경", description = "사용자의 비밀번호를 변경합니다.")
    public ResponseEntity<?> changePassword(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody

    ) {
        String password = requestBody.get("password");
        userService.updatePassword(principalUser.getUser(), password);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/changeInfo/email")
    public ResponseEntity<?> changeEmail(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody)
    {
        String email = requestBody.get("email");
        userService.updateEmail(principalUser.getUser(), email);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/changeInfo/phoneNumber")
    public ResponseEntity<?> changePhoneNumber(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody

    ) {
        String phoneNumber = requestBody.get("phoneNumber");
        userService.updatePhoneNumber(principalUser.getUser(), phoneNumber);
        return ResponseEntity.ok().build();
    }



}
