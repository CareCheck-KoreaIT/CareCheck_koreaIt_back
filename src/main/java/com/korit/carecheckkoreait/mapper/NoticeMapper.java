package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.NoticeSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.korit.carecheckkoreait.entity.Notice;

import java.util.List;

@Mapper
public interface NoticeMapper {
//    int selectNoticeAllBySearchText(@Param("searchText") String searchText);
    int insertNotice(Notice notice);
    
    List<NoticeSearch> selectAllNoticeList(
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount
    );

    int updateNoticeByNoticeId(Notice notice);

    int deleteNotice(int noticeId);


}