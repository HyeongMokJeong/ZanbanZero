package com.hanbat.zanbanzero.exception.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

public class SetFilterException {

    public static void setResponse(HttpServletRequest request, HttpServletResponse response, int status, String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        FilterExceptionTemplate filterExceptionTemplate = new FilterExceptionTemplate(new Date().toString(), message, request.getRequestURI(), status);
        String result;
        response.setStatus(status);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            result = objectMapper.writeValueAsString(filterExceptionTemplate);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        try {
            response.getWriter().write(result);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
