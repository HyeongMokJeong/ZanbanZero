package com.hanbat.zanbanzero.auth.Login.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbat.zanbanzero.Entity.user.Manager;
import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.auth.Login.UserDetails.ManagerPrincipalDetails;
import com.hanbat.zanbanzero.auth.Login.UserDetails.UserPrincipalDetails;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class LonginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private final String path;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if ("/login/user".equals(((HttpServletRequest) request).getRequestURI()) || "/login/manager".equals(((HttpServletRequest) request).getRequestURI())) {
            super.doFilter(request, response, chain);
        } else {
            chain.doFilter(request, response);
        }
    }

    public LonginFilter(String path, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setFilterProcessesUrl(path);

        this.path = path;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        UsernamePasswordAuthenticationToken token = null;

        if (path.equals("/login/user")) {
            try {
                User user = objectMapper.readValue(request.getInputStream(), User.class);
                token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (path.equals("/login/manager")) {
            try {
                Manager manager = objectMapper.readValue(request.getInputStream(), Manager.class);
                token = new UsernamePasswordAuthenticationToken(manager.getUsername(), manager.getPassword());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        token.setDetails(path);

        Authentication authentication = authenticationManager.authenticate(token);
        // Spring의 권한 관리를 위해 return을 통해 세션에 저장
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        UserDetails principalDetails = (UserDetails) authResult.getPrincipal();
        if (request.getRequestURI().equals("/login/user")) {
            principalDetails = (UserPrincipalDetails) authResult.getPrincipal();
        }
        else if (request.getRequestURI().equals("/login/manager")) {
            principalDetails = (ManagerPrincipalDetails) authResult.getPrincipal();
        }

        // HMAC256
        String JwtToken = JwtUtil.createToken(principalDetails, request.getRequestURI());
        String RefreshToken = JwtUtil.createRefreshToken(principalDetails);

        response.addHeader(JwtTemplate.HEADER_STRING, JwtTemplate.TOKEN_PREFIX + JwtToken);
        response.addHeader(JwtTemplate.REFRESH_HEADER_STRING, JwtTemplate.TOKEN_PREFIX + RefreshToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SetFilterException.setResponse(request, response, HttpStatus.UNAUTHORIZED, "인증에 실패하였습니다.");;
    }
}