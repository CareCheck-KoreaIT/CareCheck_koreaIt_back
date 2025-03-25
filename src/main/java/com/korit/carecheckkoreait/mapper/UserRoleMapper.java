package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.UserRole;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole userRole);
    List<UserRole> selectByRoleId(int roleId);
}
