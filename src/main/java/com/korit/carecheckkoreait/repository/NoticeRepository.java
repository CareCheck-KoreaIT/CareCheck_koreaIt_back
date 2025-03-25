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

    public List<NoticeSearch> findNoticeListAllBySearchOption(
            int startIndex,
            int limitSize,
            String order,
            String searchText) {
        return noticeMapper.selectNoticeBySearchOption(startIndex, limitSize, order, searchText);
    }

    public List<NoticeSearch> findNoticeListSearchByUsercode(
            String usercode,
            int startIndex,
            int limitSize,
            String order,
            String searchText) {
        return noticeMapper.selectNoticeByUsercode(usercode, startIndex, limitSize, order, searchText);
    }

    public int findNoticeCountAllBySearchText(String searchText) {
        return noticeMapper.selectNoticeCountAllBySearchText(searchText);
    }

    public int findNoticeCountUsercodeBySearchText(String usercode, String searchText) {
        return noticeMapper.selectNoticeCountUsercodeBySearchText(usercode, searchText);
    }

    public Optional<Boolean> updateUserById(String usercode, Notice notice, int noticeId) {
        return noticeMapper.updateNoticeByNoticeId(usercode, notice, noticeId) < 1
                ? Optional.empty()
                : Optional.of(true);
    }
    
    public int deleteNoticeById(int noticeId) {
        int result = noticeMapper.deleteNotice(noticeId);
        return result;
    }

    public void updateViewCount(int noticeId) {
        noticeMapper.increaseViewCount(noticeId);
    }

}