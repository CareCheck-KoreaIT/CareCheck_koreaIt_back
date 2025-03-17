package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqNoticeListSearchDto;
import com.korit.carecheckkoreait.entity.NoticeSearch;
import com.korit.carecheckkoreait.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    @Transactional(readOnly = true) //읽기전용 최적화
    public List<NoticeSearch> getNoticeListSearch(ReqNoticeListSearchDto reqNoticeListSearchDto) {
        int startIndex = (reqNoticeListSearchDto.getPage() - 1) * reqNoticeListSearchDto.getLimitCount();
        return noticeRepository.findNoticeListAll(
                startIndex,
                reqNoticeListSearchDto.getLimitCount()
        );
    }
}
