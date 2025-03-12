package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqSignupDto;
import com.korit.carecheckkoreait.entity.User;
import com.korit.carecheckkoreait.entity.UserRole;
import com.korit.carecheckkoreait.repository.UserRepository;
import com.korit.carecheckkoreait.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional(rollbackFor = Exception.class)
    public User signup(ReqSignupDto reqSignupDto) {
        User user = User.builder()
                .username(reqSignupDto.getUsername())
                .password(passwordEncoder.encode(reqSignupDto.getPassword()))
                .email(reqSignupDto.getEmail())
                .accountExpired(1)
                .accountLocked(1)
                .credentialsExpired(1)
                .accountEnabled(1)
                .build();
        userRepository.insert(user);

        UserRole userRole = UserRole.builder()
                .userId(user.getUserId())
                .roleId(reqSignupDto.getRoleId())
                .build();
        userRoleRepository.insert(userRole);

        return user;
    }
}
