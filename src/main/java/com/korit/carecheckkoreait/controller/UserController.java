package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqSearchUserDto;
import com.korit.carecheckkoreait.dto.request.ReqSignupDto;
import com.korit.carecheckkoreait.dto.response.RespUserListSearchDto;
import com.korit.carecheckkoreait.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@ModelAttribute ReqSearchUserDto dto) {
//        System.out.println("getUsers 호출");
        int totalUserListCount = userService.getUserListCountBySearchName(dto.getSearchName());
        int totalPages = totalUserListCount % dto.getLimitCount() == 0
                ? totalUserListCount / dto.getLimitCount()
                : totalUserListCount / dto.getLimitCount() + 1;

        RespUserListSearchDto respUserListSearchDto =
                RespUserListSearchDto.builder()
                        .page(dto.getPage())
                        .limitCount(dto.getLimitCount())
                        .totalPages(totalPages)
                        .totalElements(totalUserListCount)
                        .isFirstPage(dto.getPage() == 1)
                        .isLastPage(dto.getPage() == totalPages)
                        .userSearchList(userService.getUserListSearchBySearchOption(dto))
                        .build();
        return ResponseEntity.ok().body(respUserListSearchDto);
    }

    @PutMapping("/users/{usercode}")
    public ResponseEntity<?> updateUser(
            @PathVariable String usercode,
            @RequestBody Map<String, String> requestBody
    ) {
        System.out.println(requestBody);
        userService.updateUser(usercode, requestBody);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{usercode}/password")
    public ResponseEntity<?> initialUserPassword(
            @PathVariable String usercode,
            @RequestBody Map<String, String> requestBody
    ) {
        System.out.println(usercode + ": " + requestBody.get("password"));
        String initialPassword = requestBody.get("password");
        userService.initialPassword(usercode, initialPassword);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{usercode}/account")
    public ResponseEntity<?> resignationUser(@PathVariable String usercode) {
        System.out.println(usercode);
        userService.updateUserAccount(usercode);
        return ResponseEntity.ok().build();
    }

}