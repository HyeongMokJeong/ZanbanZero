package com.hanbat.zanbanzero.exception.controller;

import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.exception.controller.exceptions.JwtException;
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
     HttpStatus status;

    @ExceptionHandler(SameUsernameException.class)
    public final ResponseEntity<Object> sameUsername(Exception ex, WebRequest request){
        status = HttpStatus.CONFLICT;
        FilterExceptionTemplate exceptionResponse = new FilterExceptionTemplate(new Date().toString(), ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI(), status.value());
        return new ResponseEntity(exceptionResponse, status);
    }

    @ExceptionHandler(JwtException.class)
    public final ResponseEntity<Object> jwt(Exception ex, WebRequest request){
        status = HttpStatus.FORBIDDEN;
        FilterExceptionTemplate exceptionResponse = new FilterExceptionTemplate(new Date().toString(), ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI(), status.value());
        return new ResponseEntity(exceptionResponse, status);
    }

    @ExceptionHandler(CantFindByIdException.class)
    public final ResponseEntity<Object> cantFindById(Exception ex, WebRequest request){
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        FilterExceptionTemplate exceptionResponse = new FilterExceptionTemplate(new Date().toString(), ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI(), status.value());
        return new ResponseEntity(exceptionResponse, status);
    }
}