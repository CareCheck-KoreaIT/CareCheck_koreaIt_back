package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.NoticeSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
//    int selectNoticeAllBySearchText(@Param("searchText") String searchText);

    List<NoticeSearch> selectAllNoticeList(
            @Param("startIndex") int startIndex,
            @Param("limitCount") int limitCount
    );
}
