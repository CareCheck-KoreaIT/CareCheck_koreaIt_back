package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectById(int userId);
    User selectByUsername(String username);

    User selectByUsercode(String usercode);

    String selectUsercode(int roleId);
    int insert(User user);

    int updatePasswordById(
            @Param("usercode") String usercode,
            @Param("password") String password);
    int updateEmailById(
            @Param("usercode") String usercode,
            @Param("email") String email);
    int updatePhoneNumberById(
            @Param("usercode") String usercode,
            @Param("phoneNumber") String phoneNumber);

}
