package com.hanbat.zanbanzero.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hanbat.zanbanzero.Entity.user.Manager;
import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.auth.Login.UserDetails.ManagerPrincipalDetails;
import com.hanbat.zanbanzero.auth.Login.UserDetails.UserPrincipalDetails;
import com.hanbat.zanbanzero.repository.user.ManagerRepository;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import com.hanbat.zanbanzero.template.JwtTemplate;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.*;

public class JwtAuthFilter extends BasicAuthenticationFilter {
    
    private UserRepository userRepository;
    private ManagerRepository managerRepository;


    public JwtAuthFilter(AuthenticationManager authenticationManager, UserRepository userRepository, ManagerRepository managerRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader(JwtTemplate.HEADER_STRING);

        // JWT(Header)가 있는지 확인
        if ((jwtHeader == null || !jwtHeader.startsWith(JwtTemplate.TOKEN_PREFIX_MANAGER) && !jwtHeader.startsWith(JwtTemplate.TOKEN_PREFIX_USER))) {
            if (request.getRequestURI().toString().equals("/join") || request.getRequestURI().toString().equals("/login/user") || request.getRequestURI().toString().equals("/login/manager")){
                chain.doFilter(request, response);
                return;
            }
            throw new IOException("토큰이 없거나 잘못되었습니다.");
        }

        String token_type = (jwtHeader.startsWith(JwtTemplate.TOKEN_PREFIX_MANAGER)) ? JwtTemplate.TOKEN_PREFIX_MANAGER : JwtTemplate.TOKEN_PREFIX_USER;
        // JWT 검증
        String jwtToken = jwtHeader.replace(token_type, "");

        String username = JWT.require(Algorithm.HMAC256(JwtTemplate.SECRET)).build().verify(jwtToken).getClaim("username").asString();

        if (username != null) {
            if (token_type.equals(JwtTemplate.TOKEN_PREFIX_USER)) {
                User user = userRepository.findByUsername(username);

                UserPrincipalDetails principalDetails = new UserPrincipalDetails(user);
                // JWT 서명을 통해서 서명이 정상이면 Authentication 객체 만들어 줌
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

                // SecurityContextHolder = 시큐리티 세션 공간에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            else if (token_type.equals(JwtTemplate.TOKEN_PREFIX_MANAGER)) {
                Manager manager = managerRepository.findByUsername(username);

                ManagerPrincipalDetails principalDetails = new ManagerPrincipalDetails(manager);
                // JWT 서명을 통해서 서명이 정상이면 Authentication 객체 만들어 줌
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

                // SecurityContextHolder = 시큐리티 세션 공간에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        }
    }
}
