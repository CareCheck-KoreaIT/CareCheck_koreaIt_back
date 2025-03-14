package com.korit.carecheckkoreait.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SettingMapper {
    int updateScoreById(@Param("scorePay") double scorePay);
}
