package com.hanbat.zanbanzero.auth.Login.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbat.zanbanzero.Entity.user.Manager;
import com.hanbat.zanbanzero.auth.Login.UserDetails.ManagerPrincipalDetails;
import com.hanbat.zanbanzero.auth.jwt.JwtUtil;
import com.hanbat.zanbanzero.exception.filter.SetFilterException;
import com.hanbat.zanbanzero.auth.jwt.JwtTemplate;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class ManagerLonginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private String path;

    public ManagerLonginFilter(String path, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setFilterProcessesUrl(path);

        this.path = path;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if ("/login/manager".equals(((HttpServletRequest) request).getRequestURI())) {
            super.doFilter(request, response, chain);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        Manager manager;

        try {
            manager = objectMapper.readValue(request.getInputStream(), Manager.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(manager.getUsername(), manager.getPassword());
        token.setDetails(path);

        Authentication authentication = authenticationManager.authenticate(token);

        // Spring의 권한 관리를 위해 return을 통해 세션에 저장
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ManagerPrincipalDetails principalDetails = (ManagerPrincipalDetails) authResult.getPrincipal();

        // HMAC256
        String JwtToken = JwtUtil.createToken(principalDetails);

        response.addHeader(JwtTemplate.HEADER_STRING, JwtTemplate.TOKEN_PREFIX_MANAGER + JwtToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        SetFilterException.setResponse(request, response, HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다.");;
    }
}