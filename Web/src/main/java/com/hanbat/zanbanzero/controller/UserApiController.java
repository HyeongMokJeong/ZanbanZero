package com.hanbat.zanbanzero.controller;

import com.hanbat.zanbanzero.exception.filter.FilterExceptionTemplate;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.SameUsernameException;
import com.hanbat.zanbanzero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/join")
    public ResponseEntity<String> join(@RequestBody UserDto dto) throws SameUsernameException {
        Boolean result = userService.join(dto);
        if (!result) {
            throw new SameUsernameException("중복된 ID 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("회원가입에 성공했습니다.");
    }

    @PutMapping("/api/logout")
    public ResponseEntity<FilterExceptionTemplate> logout() {
        return null;
    }

    @GetMapping("/api/user/info")
    public ResponseEntity<UserDto> getInfo(@RequestBody UserDto dto) {
        UserDto user = userService.getInfo(dto);
        user.setPassword(null);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
