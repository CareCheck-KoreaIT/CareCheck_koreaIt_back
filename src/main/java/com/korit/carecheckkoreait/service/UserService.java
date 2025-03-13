package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqSigninDto;
import com.korit.carecheckkoreait.dto.request.ReqSignupDto;
import com.korit.carecheckkoreait.entity.User;
import com.korit.carecheckkoreait.entity.UserRole;
import com.korit.carecheckkoreait.repository.UserRepository;
import com.korit.carecheckkoreait.repository.UserRoleRepository;
import com.korit.carecheckkoreait.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public String signin(ReqSigninDto reqSigninDto) {
        User user = userRepository.selectByUsercode(reqSigninDto.getUsercode())
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 다시 확인하세요."));

        if(!passwordEncoder.matches(reqSigninDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 다시 확인하세요.");
        }

        Date expires = new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 7);

        return jwtUtil.generateToken(
                user.getUsercode(),
                Integer.toString(user.getIndex()),
                expires
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public User signup(ReqSignupDto reqSignupDto) {
        String usercode = userRepository.selectUsercode(reqSignupDto.getRoleId());

        User user = User.builder()
                .username(reqSignupDto.getUsername())
                .usercode(usercode)
                .password(passwordEncoder.encode(reqSignupDto.getPassword()))
                .email(reqSignupDto.getEmail())
                .accountExpired(1)
                .accountLocked(1)
                .credentialsExpired(1)
                .accountEnabled(1)
                .build();
        userRepository.insert(user);

        UserRole userRole = UserRole.builder()
                .userId(user.getIndex())
                .roleId(reqSignupDto.getRoleId())
                .build();
        userRoleRepository.insert(userRole);

        return user;
    }
}
