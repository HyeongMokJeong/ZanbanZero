package com.hanbat.zanbanzero.exception.controller;

import com.hanbat.zanbanzero.exception.filter.SetFilterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        SetFilterException.setResponse(request, response, HttpServletResponse.SC_FORBIDDEN, "인증에 실패하였습니다.");
    }
}
