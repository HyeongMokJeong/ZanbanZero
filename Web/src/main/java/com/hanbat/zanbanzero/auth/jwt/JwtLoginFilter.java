package com.hanbat.zanbanzero.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.auth.PrincipalDetails;
import com.hanbat.zanbanzero.exception.filter.SetFilterException;
import com.hanbat.zanbanzero.template.JwtTemplate;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

// Security Session > Security ContextHolder : Authentication > UserDetails
@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;

        // request에서 username, password 받아서 user 객체 생성
        try {
            user = objectMapper.readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // JWT 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        
        // 로그인 시도 -> UserDetailsService(PrincipalDetailsService)의 loadUserByUsername 실행
        // DB의 username과 password가 일치하면 authentication 리턴됨
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (NullPointerException e) {
            SetFilterException.setResponse(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"인증에 실패하였습니다.");
        }

        // Spring의 권한 관리를 위해 return을 통해 세션에 저장
        return authentication;
    }

    // 인증 성공 시 JWT 발급
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // HMAC256
        String JwtToken = JwtUtil.createToken(principalDetails);

        response.addHeader(JwtTemplate.HEADER_STRING, JwtTemplate.TOKEN_PREFIX + JwtToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        SetFilterException.setResponse(request, response, HttpServletResponse.SC_UNAUTHORIZED, "인증에 실패하였습니다.");
    }
}
