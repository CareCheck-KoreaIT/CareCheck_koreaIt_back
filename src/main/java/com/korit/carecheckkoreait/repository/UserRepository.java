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
}
