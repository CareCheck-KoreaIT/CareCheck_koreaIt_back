package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqWriteNoticeDto;
import com.korit.carecheckkoreait.security.principal.PrincipalUser;
import com.korit.carecheckkoreait.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Operation(summary = "공지사항 작성", description = "공지사항 작성")
    @PostMapping("/write")
    public ResponseEntity<?> createNotice(
            @RequestBody ReqWriteNoticeDto reqWriteNoticeDto,
            @AuthenticationPrincipal PrincipalUser principalUser
    ) {
        return ResponseEntity.ok().body(noticeService.createNotice(principalUser.getUser(), reqWriteNoticeDto));
    }
}