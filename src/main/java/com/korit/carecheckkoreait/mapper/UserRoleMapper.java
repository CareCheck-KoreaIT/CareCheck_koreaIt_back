package com.korit.carecheckkoreait.mapper;

import com.korit.carecheckkoreait.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole userRole);
}
