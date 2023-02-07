package com.hanbat.zanbanzero.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.auth.PrincipalDetails;
import com.hanbat.zanbanzero.exception.filter.SetFilterException;
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

import java.io.IOException;

public class JwtAuthFilter extends BasicAuthenticationFilter {
    
    private UserRepository userRepository;


    public JwtAuthFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader(JwtTemplate.HEADER_STRING);

        // JWT(Header)가 있는지 확인
        if (jwtHeader == null || !jwtHeader.startsWith(JwtTemplate.TOKEN_PREFIX)) {
            SetFilterException.setResponse(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "잘못된 토큰입니다.");
            return;
        }

        // JWT 검증
        String jwtToken = request.getHeader(JwtTemplate.HEADER_STRING).replace(JwtTemplate.TOKEN_PREFIX, "");

        String username = null;
        try {
            username = JWT.require(Algorithm.HMAC256(JwtTemplate.SECRET)).build().verify(jwtToken).getClaim("username").asString();
        }
        catch (TokenExpiredException e) {
            SetFilterException.setResponse(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "만료된 토큰입니다.");
        }
        if (username != null) {
            User userEntity = userRepository.findByUsername(username);

            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);

            // JWT 서명을 통해서 서명이 정상이면 Authentication 객체 만들어 줌
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // SecurityContextHolder = 시큐리티 세션 공간에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 기존 응답으로 chain
            chain.doFilter(request, response);
        }
    }
}
