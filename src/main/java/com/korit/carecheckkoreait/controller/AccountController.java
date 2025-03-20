package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqChangeEmailDto;
import com.korit.carecheckkoreait.security.principal.PrincipalUser;
import com.korit.carecheckkoreait.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account/users")
public class AccountController {

    @Autowired
    private UserService userService;

    @PutMapping("/changeInfo/password")
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
            @RequestBody ReqChangeEmailDto reqChangeEmailDto
    ) {
        String email = reqChangeEmailDto.getEmail();
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

    @GetMapping("/me")
    public ResponseEntity<?> getLoginUser(@AuthenticationPrincipal PrincipalUser principalUser) {
        // System.out.println(principalUser.getUser());      // 테스트
        return ResponseEntity.ok().body(principalUser.getUser());
    }


}
