package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.entity.User;
import com.korit.carecheckkoreait.security.principal.PrincipalUser;
import com.korit.carecheckkoreait.service.UserService;
import org.apache.coyote.BadRequestException;
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

    @PutMapping("/password")
    public ResponseEntity<?> changePassword(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
    ) throws Exception {

        userService.updatePassword(principalUser, requestBody.get("currentPassword"), requestBody.get("newPassword"));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/email")
    public ResponseEntity<?> changeEmail(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
    ) {
        String newEmail = requestBody.get("email");
        userService.updateEmail(principalUser, newEmail);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/phone-number")
    public ResponseEntity<?> changePhoneNumber(
            @AuthenticationPrincipal PrincipalUser principalUser,
            @RequestBody Map<String, String> requestBody
    ) {
        String newPhoneNumber = requestBody.get("phoneNumber");
        userService.updatePhoneNumber(principalUser, newPhoneNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getLoginUser(@AuthenticationPrincipal PrincipalUser principalUser) {
        // System.out.println(principalUser.getUser());      // 테스트
        return ResponseEntity.ok().body(principalUser.getUser());
    }


}
