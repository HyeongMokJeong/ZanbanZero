package com.hanbat.zanbanzero.controller.user;

import com.hanbat.zanbanzero.dto.user.ManagerDto;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.JwtException;
import com.hanbat.zanbanzero.service.user.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerApiController {
    private final ManagerService managerService;

    @GetMapping("/api/manager/info")
    public ResponseEntity<ManagerDto> getInfo(@RequestBody ManagerDto dto, @RequestHeader("Authorization") String token) throws JwtException {
        ManagerDto managerDto = managerService.getInfo(dto, token);
        if (managerDto == null) {
            throw new JwtException("유저명이 토큰의 이름과 다릅니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(managerDto);
    }
}
