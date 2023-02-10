package com.hanbat.zanbanzero.controller;

import com.hanbat.zanbanzero.exception.controller.exceptions.JwtException;
import com.hanbat.zanbanzero.exception.filter.FilterExceptionTemplate;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.SameUsernameException;
import com.hanbat.zanbanzero.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto dto, @RequestHeader("Authorization") String token) throws SameUsernameException, JwtException {
        int result = userService.join(dto, token);
        if (result == 0) {
            throw new SameUsernameException("중복된 ID 입니다.");
        }
        if (result == 2) {
            throw new JwtException("잘못된 토큰입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("회원가입에 성공했습니다.");
    }

    @PutMapping("/api/logout")
    public ResponseEntity<FilterExceptionTemplate> logout() {
        return null;
    }

    @GetMapping("/api/userInfo")
    public ResponseEntity<UserDto> getInfo(@RequestBody UserDto dto, @RequestHeader("Authorization") String token) throws JwtException {
        UserDto user = userService.getInfo(dto, token);
        if (user == null) {
            throw new JwtException("유저명이 토큰의 이름과 다릅니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}