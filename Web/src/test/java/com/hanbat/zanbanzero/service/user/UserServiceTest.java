package com.hanbat.zanbanzero.service.user;

import com.hanbat.zanbanzero.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void signup() {
        // 준비
        //UserDto dto = new UserDto("test", "1234");
        //userRepository.save(User.createCommonUser(dto));

        // 실제
        Long target = userRepository.doubleCheckUsername("test");

        // 예상
        Long expected = 1L;

        // 비교
        assertEquals(target, expected);
    }
}