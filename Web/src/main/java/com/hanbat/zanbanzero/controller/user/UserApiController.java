package com.hanbat.zanbanzero.controller.user;

import com.hanbat.zanbanzero.dto.info.UserInfoDto;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.JwtException;
import com.hanbat.zanbanzero.exception.filter.ExceptionTemplate;
import com.hanbat.zanbanzero.exception.controller.exceptions.SameNameException;
import com.hanbat.zanbanzero.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto dto) throws SameNameException {
        int result = userService.join(dto);
        if (result == 0) {
            throw new SameNameException("중복된 아이디입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("회원가입에 성공했습니다.");
    }

    @PutMapping("/logout")
    public ResponseEntity<ExceptionTemplate> logout() {
        return null;
    }

    @GetMapping("/api/user/info")
    public ResponseEntity<UserInfoDto> getInfo(@RequestBody UserDto dto, @RequestHeader("Authorization") String token) throws JwtException {
        UserInfoDto user = userService.getInfo(dto, token);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}