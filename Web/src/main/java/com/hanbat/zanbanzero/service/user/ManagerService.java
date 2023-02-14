package com.hanbat.zanbanzero.service.user;

import com.hanbat.zanbanzero.Entity.store.Store;
import com.hanbat.zanbanzero.Entity.user.Manager;
import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.auth.jwt.JwtUtil;
import com.hanbat.zanbanzero.dto.store.StoreDto;
import com.hanbat.zanbanzero.dto.user.ManagerDto;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.JwtException;
import com.hanbat.zanbanzero.repository.user.ManagerRepository;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private  final JwtUtil jwtUtil;

    private final ManagerRepository managerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ManagerDto getInfo(ManagerDto dto, String token) throws JwtException {
        if (!jwtUtil.checkJwt(dto.getUsername(), token)) {
            throw new JwtException("토큰과 유저명이 다릅니다.");
        }

        Manager manager = managerRepository.findByUsername(dto.getUsername());
        ManagerDto result = ManagerDto.createManagerDto(manager);
        result.setPassword(null);

        return result;
    }

}
