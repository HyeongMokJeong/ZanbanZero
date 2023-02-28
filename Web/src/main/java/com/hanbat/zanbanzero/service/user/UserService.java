package com.hanbat.zanbanzero.service.user;

import com.hanbat.zanbanzero.entity.user.User.User;
import com.hanbat.zanbanzero.auth.jwt.JwtUtil;
import com.hanbat.zanbanzero.dto.info.UserInfoDto;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.JwtException;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public int join(UserDto dto) {

        if (userRepository.doubleCheckUsername(dto.getUsername()) == 1)
            return 0;
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        User userAuth = User.createUser(dto);
        userRepository.save(userAuth);
        return 1;
    }

    public UserInfoDto getInfo(UserDto dto, String token) throws JwtException {
        if (!jwtUtil.checkJwt(dto.getUsername(), token)) {
            throw new JwtException("토큰과 유저명이 다릅니다.");
        }

        User user = userRepository.findByUsername(dto.getUsername());
        UserInfoDto userInfoDto = new UserInfoDto(user.getId(), user.getUsername());

        return userInfoDto;
    }
}
