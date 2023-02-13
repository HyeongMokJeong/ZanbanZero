package com.hanbat.zanbanzero.service.user;

import com.hanbat.zanbanzero.Entity.user.Manager;
import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.auth.jwt.JwtUtil;
import com.hanbat.zanbanzero.dto.user.ManagerDto;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.repository.user.ManagerRepository;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private JwtUtil jwtUtil = new JwtUtil();

    private final ManagerRepository managerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ManagerDto getInfo(ManagerDto dto, String token) {
        if (!dto.getUsername().equals(jwtUtil.getUsernameFromToken(token))) {
            return null;
        }

        Manager manager = managerRepository.findByUsername(dto.getUsername());
        ManagerDto result = ManagerDto.createManagerDto(manager);
        result.setPassword(null);

        return result;
    }
}
