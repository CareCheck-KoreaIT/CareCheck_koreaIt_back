package com.korit.carecheckkoreait.repository;

import com.korit.carecheckkoreait.entity.User;
import com.korit.carecheckkoreait.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public Optional<User> selectById(int userId) {
        return Optional.ofNullable(userMapper.selectById(userId));
    }

    public Optional<User> selectByUsercode(String usercode) {
        return Optional.ofNullable(userMapper.selectByUsercode(usercode));
    }

    public String selectUsercode(int roleId) {
        return userMapper.selectUsercode(roleId);
    }

    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }
    public void updatePassword(String usercode, String password) {
        userMapper.updatePasswordById(usercode, password);
    }
    public void updateEmail(String usercode, String email) {
        userMapper.updateEmailById(usercode, email);
    }
    public void updatePhoneNumber(String usercode, String phoneNumber) {
        userMapper.updatePhoneNumberById(usercode, phoneNumber);
    }

}
