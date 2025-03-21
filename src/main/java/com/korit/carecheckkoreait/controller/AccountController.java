package com.korit.carecheckkoreait.controller;

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

    @PutMapping("/{usercode}/email")
    public ResponseEntity<?> changeEmail(
            @PathVariable String usercode,
            @RequestBody Map<String, String> requestBody
    ) {
        String newEmail = requestBody.get("email");
        System.out.println(usercode + " " + newEmail);
        userService.updateEmail(usercode, newEmail);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{usercode}/phoneNumber")
    public ResponseEntity<?> changePhoneNumber(
            @PathVariable String usercode,
            @RequestBody Map<String, String> requestBody
    ) {
        String newPhoneNumber = requestBody.get("phoneNumber");
        userService.updatePhoneNumber(usercode, newPhoneNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getLoginUser(@AuthenticationPrincipal PrincipalUser principalUser) {
        // System.out.println(principalUser.getUser());      // 테스트
        return ResponseEntity.ok().body(principalUser.getUser());
    }


}
