package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqModifyNoticeDto;
import com.korit.carecheckkoreait.dto.request.ReqNoticeListSearchDto;
import com.korit.carecheckkoreait.entity.NoticeSearch;
import com.korit.carecheckkoreait.dto.request.ReqWriteNoticeDto;
import com.korit.carecheckkoreait.entity.Notice;
import com.korit.carecheckkoreait.entity.User;
import com.korit.carecheckkoreait.repository.NoticeRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<NoticeSearch> getNoticeListSearchBySearchOption(ReqNoticeListSearchDto dto) {
        int startIndex = dto.getPage() * dto.getLimitCount() - dto.getLimitCount(); // 페이지 인덱스 계산
        int limitSize = dto.getLimitCount();
        String order = dto.getOrder();
        String searchText = dto.getSearchText();
        return noticeRepository.findNoticeListAllBySearchOption(
                startIndex, limitSize, order, searchText
        );
    }

    public List<NoticeSearch> getNoticeListSearchByUsercode(String usercode, String searchText, int page, int limitCount, String order) {
        int startIndex = (page - 1) * limitCount;
        int limitSize = limitCount;

        return noticeRepository.findNoticeListSearchByUsercode(usercode, startIndex, limitSize, order, searchText);
    }


    @Transactional(rollbackFor = Exception.class)
    public Boolean modiftyNotice(int noticeId, ReqModifyNoticeDto reqModifyNoticeDto) throws NotFoundException {
        return noticeRepository.updateUserById(reqModifyNoticeDto.toNotice(noticeId))
                .orElseThrow(() -> new NotFoundException("해당 게시글이 존재하지 않습니다."));
   }

    @Transactional(rollbackFor = Exception.class)
    public int deleteNoticeById(int noticeId) {
        return  noticeRepository.deleteNoticeById(noticeId);

    }

//    @Transactional(rollbackFor = Exception.class)
//    public int updateViewCount(int noticeId) {
//        return noticeRepository.updateViewCount(noticeId);
//    }

    @Transactional(readOnly = true)
    public int getNoticeListCountBySearchText(String searchText) {
        return noticeRepository.findNoticeCountAllBySearchText(searchText);
    }

    @Transactional(readOnly = true)
    public int getNoticeListCountUsercodeBySearchText(String usercode, String searchText) {
        return noticeRepository.findNoticeCountUsercodeBySearchText(usercode, searchText);
    }
}
