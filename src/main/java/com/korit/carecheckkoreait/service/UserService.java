package com.korit.carecheckkoreait.service;

import com.korit.carecheckkoreait.dto.request.ReqSearchUserDto;
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
import java.util.List;

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
                .phoneNumber(reqSignupDto.getPhoneNumber())
                .accountExpired(1)
                .accountLocked(1)
                .credentialsExpired(1)
                .accountEnabled(1)
                .build();
        userRepository.insert(user);

        UserRole userRole = UserRole.builder()
                .usercode(user.getUsercode())
                .roleId(reqSignupDto.getRoleId())
                .build();
        userRoleRepository.insert(userRole);

        return user;
    }
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(User user, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.updatePassword(user.getUsercode(), encodedPassword);
    }
    @Transactional(rollbackFor = Exception.class)
    public void updateEmail(String usercode, String email) {
        User foundUser = userRepository.selectByUsercode(usercode)
                .orElseThrow(() -> new BadCredentialsException("사용자의 정보를 조회할 수 없습니다."));
        userRepository.updateEmail(foundUser.getUsercode(), email);
    }
    public void updatePhoneNumber(String usercode, String phoneNumber) {
        User foundUser = userRepository.selectByUsercode(usercode)
                .orElseThrow(() -> new BadCredentialsException("사용자의 정보를 조회할 수 없습니다."));
        userRepository.updatePhoneNumber(foundUser.getUsercode(), phoneNumber);
    }

    @Transactional(readOnly = true)
    public List<User> getUserListSearchBySearchOption(ReqSearchUserDto reqSearchUserDto) {
        int startIndex = (reqSearchUserDto.getPage() - 1) * reqSearchUserDto.getLimitCount();
        List<User> foundUser = userRepository.selectUserListAllBySearchOption(
                startIndex,
                reqSearchUserDto.getLimitCount(),
                reqSearchUserDto.getOrder(),
                reqSearchUserDto.getSearchName()
        );
//        System.out.println("Service : " + foundUser);         // for test
        return foundUser;
    }

    @Transactional(readOnly = true)
    public int getUserListCountBySearchName(String searchName) {
        return userRepository.selectUserListCountAllBySearchName(searchName);
    }

}
