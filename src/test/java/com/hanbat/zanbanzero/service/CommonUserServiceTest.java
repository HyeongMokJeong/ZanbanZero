package com.hanbat.zanbanzero.service;

import com.hanbat.zanbanzero.Entity.user.CommonUser;
import com.hanbat.zanbanzero.dto.user.CommonUserDto;
import com.hanbat.zanbanzero.repository.user.CommonUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommonUserServiceTest {

    @Autowired
    CommonUserRepository commonUserRepository;

    @Test
    void signup() {
        // 준비
        CommonUserDto dto = new CommonUserDto("test", "1234");
        commonUserRepository.save(CommonUser.createCommonUser(dto));

        // 실제
        Boolean target = commonUserRepository.doubleCheckUserId("test");

        // 예상
        Boolean expected = true;

        // 비교
        assertEquals(target, expected);
    }
}