package com.korit.carecheckkoreait.repository;

import com.korit.carecheckkoreait.entity.UserRole;
import com.korit.carecheckkoreait.mapper.UserRoleMapper;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepository {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public UserRole insert(UserRole userRole) {
        userRoleMapper.insert(userRole);
        return userRole;
    }

    public Optional<List<UserRole>> selectUserCodyByRoleId(int roleId) {
        List<UserRole> usercodeInRoleId = userRoleMapper.selectByRoleId(roleId);
        if(usercodeInRoleId == null) {
            Optional.empty();
        }
        return Optional.of(usercodeInRoleId);
    }
}
