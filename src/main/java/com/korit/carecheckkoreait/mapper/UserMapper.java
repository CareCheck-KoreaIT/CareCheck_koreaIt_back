package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectById(int userId);

    int insert(User user);
}
