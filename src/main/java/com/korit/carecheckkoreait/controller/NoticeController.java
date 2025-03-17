package com.korit.carecheckkoreait.controller;

import com.korit.carecheckkoreait.dto.request.ReqNoticeListSearchDto;
import com.korit.carecheckkoreait.dto.response.RespNoticeListSearchDto;
import com.korit.carecheckkoreait.entity.NoticeSearch;
import com.korit.carecheckkoreait.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public ResponseEntity<?> searchNoticeList(@ModelAttribute ReqNoticeListSearchDto dto) {
        List<NoticeSearch> NoticeList = noticeService.getNoticeListSearch(dto);
        int totalNoticeListCount = NoticeList.size();
        int totalPages = totalNoticeListCount % dto.getLimitCount() == 0
                ? totalNoticeListCount / dto.getLimitCount()
                : totalNoticeListCount / dto.getLimitCount() + 1;

        RespNoticeListSearchDto respNoticeListSearchDto =
                RespNoticeListSearchDto.builder()
                        .page(dto.getPage())
                        .limitCount(dto.getLimitCount())
                        .totalPages(totalPages)
                        .totalElements(totalNoticeListCount)
                        .noticeList(noticeService.getNoticeListSearch(dto))
                        .build();

        return ResponseEntity.ok().body(respNoticeListSearchDto);
    }
}
