package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqWriteNoticeDto;
import com.korit.carecheckkoreait.entity.Notice;
import com.korit.carecheckkoreait.entity.User;
import com.korit.carecheckkoreait.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    @Transactional(rollbackFor = Exception.class)
    public Notice createNotice(User user, ReqWriteNoticeDto reqWriteNoticeDto) {

        Notice notice = Notice.builder()
                .usercode(user.getUsercode())
                .title(reqWriteNoticeDto.getTitle())
                .content(reqWriteNoticeDto.getContent())
                .build();
        return noticeRepository.saveNotice(notice);
    }

}
