package com.hanbat.zanbanzero.service;

import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.repository.user.UserRepository;
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

    @Transactional
    public Boolean join(UserDto dto) {
        // dto를 entity 오브젝트로 변환
        User user = User.createCommonUser(dto);

        // 중복 체크하여 중복이면 400 return
        if (userRepository.doubleCheckUsername(user.getUsername()) == 1)
            return false;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return true;
    }

    public UserDto getInfo(UserDto dto) {
        User user = userRepository.findByUsername(dto.getUsername());
        UserDto result = UserDto.createCommonUserDto(user);

        return result;
    }
}
