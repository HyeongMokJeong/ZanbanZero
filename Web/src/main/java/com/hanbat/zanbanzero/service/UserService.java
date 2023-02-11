package com.hanbat.zanbanzero.service;

import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.auth.jwt.JwtUtil;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import com.hanbat.zanbanzero.template.JwtTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private JwtUtil jwtUtil = new JwtUtil();

    @Transactional
    public int join(UserDto dto, String token) {
        if (!token.equals(JwtTemplate.JOIN_TOKEN)) {
            return 2;
        }

        // dto를 entity 오브젝트로 변환
        User user = User.createCommonUser(dto);

        // 중복 체크하여 중복이면 400 return
        if (userRepository.doubleCheckUsername(user.getUsername()) == 1)
            return 0;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setStoreId(null);
        userRepository.save(user);
        return 1;
    }

    public UserDto getInfo(UserDto dto, String token) {
        if (!dto.getUsername().equals(jwtUtil.getUsernameFromToken(token))) {
            return null;
        }

        User user = userRepository.findByUsername(dto.getUsername());
        UserDto result = UserDto.createCommonUserDto(user);
        result.setPassword(null);

        return result;
    }
}
