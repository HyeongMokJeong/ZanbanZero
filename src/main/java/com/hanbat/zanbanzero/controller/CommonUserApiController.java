package com.hanbat.zanbanzero.controller;

import com.hanbat.zanbanzero.dto.state.UserState;
import com.hanbat.zanbanzero.dto.user.CommonUserDto;
import com.hanbat.zanbanzero.service.CommonUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommonUserApiController {

    @Autowired
    CommonUserService commonUserService;

    @PostMapping("/api/cuser/new")
    public ResponseEntity<UserState> singup(@RequestBody CommonUserDto dto) {
        UserState result = commonUserService.signup(dto);

        return (result.getCode() == 200L) ?
                ResponseEntity.status(HttpStatus.OK).body(result) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @PostMapping("/api/cuser/login")
    public ResponseEntity<UserState> login(@RequestBody CommonUserDto dto, HttpServletRequest request) {
        UserState result = commonUserService.login(dto, request);

        return (result.getCode() == 200L) ?
                ResponseEntity.status(HttpStatus.OK).body(result) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @PutMapping("/api/cuser/logout")
    public ResponseEntity<UserState> logout() {
        return null;
    }

}
