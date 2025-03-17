package com.korit.carecheckkoreait.repository;

import com.korit.carecheckkoreait.entity.Notice;
import com.korit.carecheckkoreait.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeRepository {
    @Autowired
    private NoticeMapper noticeMapper;

    public Notice saveNotice(Notice notice) {
        noticeMapper.insertNotice(notice);
        return notice;
    }
}
