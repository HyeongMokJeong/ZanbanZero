package com.hanbat.zanbanzero.auth;

import com.hanbat.zanbanzero.auth.Login.Provider.ManagerAuthenticationProvider;
import com.hanbat.zanbanzero.auth.Login.Provider.UserAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserAuthenticationProvider userAuthenticationProvider;
    private final ManagerAuthenticationProvider managerAuthenticationProvider;

    public CustomAuthenticationManager(UserAuthenticationProvider userAuthenticationProvider, ManagerAuthenticationProvider managerAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.managerAuthenticationProvider = managerAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String uri = (String) authentication.getDetails();
        if (uri.endsWith("/user")) {
            return userAuthenticationProvider.authenticate(authentication);
        } else if (uri.endsWith("/manager")) {
            return managerAuthenticationProvider.authenticate(authentication);
        } else {
            throw new BadCredentialsException("CustomAuthenticationManager Error - 잘못된 uri 입니다.");
        }
    }
}
