package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectById(int userId);
    User selectByUsername(String username);

    int insert(User user);
    int updateUsername(
            @Param("username") String username
    );
}
