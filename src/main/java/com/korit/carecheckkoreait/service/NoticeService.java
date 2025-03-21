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

    @Transactional(readOnly = true)
    public List<NoticeSearch> getNoticeListSearchBySearchOption(ReqNoticeListSearchDto dto) {
        int startIndex = (dto.getPage() -1) * dto.getLimitCount(); // 페이지 인덱스 계산
        return noticeRepository.findNoticeListAllBySearchOption(
                startIndex, dto.getLimitCount(), dto.getOrder(), dto.getSearchText()
        );
    }

    public List<NoticeSearch> getNoticeListSearchByUsercode(String usercode) {
        return noticeRepository.findNoticeListSearchByUsercode(usercode);
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

    @Transactional(rollbackFor = Exception.class)
    public void updateViewCount(int noticeId) {
        noticeRepository.updateViewCount(noticeId);
    }

    @Transactional(readOnly = true)
    public int getNoticeListCountBySearchText(String searchText) {
        return noticeRepository.findNoticeCountAllBySearchText(searchText);
    }
}
