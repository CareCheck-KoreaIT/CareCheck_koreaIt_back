package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.NoticeSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.korit.carecheckkoreait.entity.Notice;

import java.util.List;

@Mapper
public interface NoticeMapper {

    int insertNotice(Notice notice);

    int updateNoticeByNoticeId(Notice notice);

    int deleteNotice(int noticeId);

    List<NoticeSearch> selectNoticeBySearchOption(
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount,
            @Param("order") String order,
            @Param("searchText") String searchText
    );

    List<NoticeSearch> selectNoticeByUsercode(@Param("usercode") String usercode);

    int selectNoticeCountAllBySearchText(@Param("searchText") String searchText);

    int increaseViewCount(int noticeId);

}