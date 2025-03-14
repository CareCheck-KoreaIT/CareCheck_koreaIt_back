package com.korit.carecheckkoreait.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SettingMapper {
    int updateScoreById(double scorePay);
}
