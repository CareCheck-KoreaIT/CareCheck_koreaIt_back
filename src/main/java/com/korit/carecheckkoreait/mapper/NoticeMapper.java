package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    int insertNotice(Notice notice);
}
