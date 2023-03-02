package com.hanbat.zanbanzero.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbat.zanbanzero.dto.user.user.UserMyPageDto;
import com.hanbat.zanbanzero.entity.user.user.User;
import com.hanbat.zanbanzero.auth.jwt.JwtUtil;
import com.hanbat.zanbanzero.dto.info.UserInfoDto;
import com.hanbat.zanbanzero.dto.user.user.UserDto;
import com.hanbat.zanbanzero.entity.user.user.UserMyPage;
import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.exception.controller.exceptions.JwtException;
import com.hanbat.zanbanzero.repository.user.UserMyPageRepository;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMyPageRepository userMyPageRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public int join(UserDto dto) throws JsonProcessingException {
        if (userRepository.doubleCheckUsername(dto.getUsername()) == 1)
            return 0;
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        dto.setRoles("ROLE_USER");

        User target = User.createUser(dto);
        User user = userRepository.save(target);

        Map<Integer, String> first_coupon = Map.of(1, "신규가입 환영 쿠폰");
        UserMyPage userMyPage = new UserMyPage(user.getId(), user, new ObjectMapper().writeValueAsString(first_coupon), 0);
        userMyPageRepository.save(userMyPage);

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

    public UserMyPageDto getMyPage(Long id) throws CantFindByIdException, JsonProcessingException {
        UserMyPage userMyPage = userMyPageRepository.getMyPage(id).orElseThrow(CantFindByIdException::new);

        UserMyPageDto userMyPageDto = UserMyPageDto.createUserMyPageDto(userMyPage);
        return userMyPageDto;
    }
}
