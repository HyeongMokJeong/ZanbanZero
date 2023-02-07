package com.hanbat.zanbanzero.exception.controller;

import com.hanbat.zanbanzero.exception.filter.FilterExceptionTemplate;
import com.hanbat.zanbanzero.exception.controller.exceptions.SameUsernameException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SameUsernameException.class)
    public final ResponseEntity<Object> sameUsername(Exception ex, WebRequest request){
        FilterExceptionTemplate exceptionResponse = new FilterExceptionTemplate(new Date().toString(), ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI(), HttpServletResponse.SC_CONFLICT);
        return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
    }
}
