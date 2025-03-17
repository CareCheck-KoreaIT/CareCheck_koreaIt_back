package com.korit.carecheckkoreait.repository;

import com.korit.carecheckkoreait.entity.NoticeSearch;
import com.korit.carecheckkoreait.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeRepository {
    @Autowired
    private NoticeMapper noticeMapper;

    public List<NoticeSearch> findNoticeListAll(
            int startIndex,
            int limitSize) {
        return noticeMapper.selectAllNoticeList(startIndex, limitSize);
    }
}


