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
  
    @Transactional(readOnly = true) //읽기전용 최적화
    public List<NoticeSearch> getNoticeListSearch(ReqNoticeListSearchDto reqNoticeListSearchDto) {
        int startIndex = (reqNoticeListSearchDto.getPage() - 1) * reqNoticeListSearchDto.getLimitCount();
        return noticeRepository.findNoticeListAll(
                startIndex,
                reqNoticeListSearchDto.getLimitCount()
        );
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

   @Transactional(rollbackFor = Exception.class)
    public Boolean modiftyNotice(int noticeId, ReqModifyNoticeDto reqModifyNoticeDto) throws NotFoundException {
        return noticeRepository.updateUserById(reqModifyNoticeDto.toNotice(noticeId))
                .orElseThrow(() -> new NotFoundException("해당 게시글이 존재하지 않습니다."));
   }

    @Transactional(rollbackFor = Exception.class)
    public int deleteNoticeById(int noticeId) {
        return  noticeRepository.deleteNoticeById(noticeId);

    }
}
