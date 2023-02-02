package com.hanbat.zanbanzero.service;

import com.hanbat.zanbanzero.Entity.user.CommonUser;
import com.hanbat.zanbanzero.dto.state.UserState;
import com.hanbat.zanbanzero.dto.user.CommonUserDto;
import com.hanbat.zanbanzero.repository.user.CommonUserRepository;
import com.hanbat.zanbanzero.session.SessionConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CommonUserService {

    @Autowired
    CommonUserRepository commonUserRepository;

    @Transactional
    public UserState signup(CommonUserDto dto) {
        // dto를 entity 오브젝트로 변환
        CommonUser commonUser = CommonUser.createCommonUser(dto);

        // 중복 체크하여 중복이면 400 return
        if (commonUserRepository.doubleCheckUserId(commonUser.getUserId()))
            return new UserState(400L, "중복된 아이디입니다.");
        commonUserRepository.save(commonUser);
        return new UserState(200L, "회원가입에 성공했습니다.");
    }

    public UserState login(CommonUserDto dto, HttpServletRequest request) {
        // 아이디로 entity 객체 받아옴
        CommonUser commonUser = commonUserRepository.findByUserId(dto.getUserId());

        // 아이디 비밀번호 대조
        if (commonUser == null || !dto.getPassword().equals(commonUser.getPassword()))
            return new UserState(400L, "ID 또는 비밀번호가 맞지 않습니다.");

        // 성공 시
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.LOGIN_MEMBER, commonUser);

        return new UserState(200L, "로그인되었습니다.");

    }
}
