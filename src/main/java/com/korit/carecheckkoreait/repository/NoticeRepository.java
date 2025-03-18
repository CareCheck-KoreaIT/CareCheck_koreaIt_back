package com.korit.carecheckkoreait.repository;

import com.korit.carecheckkoreait.dto.request.ReqModifyNoticeDto;
import com.korit.carecheckkoreait.entity.NoticeSearch;
import com.korit.carecheckkoreait.entity.Notice;
import com.korit.carecheckkoreait.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class NoticeRepository {
    @Autowired
    private NoticeMapper noticeMapper;
    
    public Notice saveNotice(Notice notice) {
        noticeMapper.insertNotice(notice);
        return notice;
    }
  
    public List<NoticeSearch> findNoticeListAll(
            int startIndex,
            int limitSize) {
        return noticeMapper.selectAllNoticeList(startIndex, limitSize);
    }

    public Optional<Boolean> updateUserById(Notice notice) {
        return noticeMapper.updateNoticeByNoticeId(notice) < 1 ? Optional.empty() : Optional.of(true);
    }


}
