package com.hanbat.zanbanzero.auth.Login.Provider;

import com.hanbat.zanbanzero.auth.Login.UserDetails.UserPrincipalDetails;
import com.hanbat.zanbanzero.auth.Login.UserDetailsService.UserPrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserPrincipalDetailsService userPrincipalDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserPrincipalDetails principalDetails = userPrincipalDetailsService.loadUserByUsername(username);

        if (password == null || !bCryptPasswordEncoder.matches(password, principalDetails.getPassword())) {
            throw new AuthenticationServiceException("인증 실패");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(principalDetails.getRoles().toString()));

        return new UsernamePasswordAuthenticationToken(principalDetails, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
