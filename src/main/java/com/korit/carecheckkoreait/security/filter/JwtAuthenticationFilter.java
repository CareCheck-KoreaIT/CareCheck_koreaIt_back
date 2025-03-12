package com.korit.carecheckkoreait.security.filter;

import com.korit.carecheckkoreait.entity.User;
import com.korit.carecheckkoreait.security.jwt.JwtUtil;
import com.korit.carecheckkoreait.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    // @Autowired
    // private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    private void jwtAuthentication(String accessToken) {
        if(accessToken == null) {return;}
        Claims claims = jwtUtil.parseToken(accessToken);

        int userId = Integer.parseInt(claims.getId());
        User user = null;
        // User user = userRepository.findById(userId).get();
        // 추후 수정

        PrincipalUser principalUser = PrincipalUser.builder().user(user).build();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getAccessToken(HttpServletRequest request) {
        String accessToken = null;
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            accessToken = authorization.substring(7);
        }

        return accessToken;
    }
}
