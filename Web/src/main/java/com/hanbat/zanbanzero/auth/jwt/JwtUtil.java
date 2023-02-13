package com.hanbat.zanbanzero.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hanbat.zanbanzero.auth.Login.UserDetails.ManagerPrincipalDetails;
import com.hanbat.zanbanzero.auth.Login.UserDetails.UserPrincipalDetails;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.template.JwtTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    public static String createToken(UserPrincipalDetails principalDetails) {
        return JWT.create()
                .withSubject(JwtTemplate.TOKEN_PREFIX_USER)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtTemplate.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC256(JwtTemplate.SECRET));
    }

    public static String createToken(ManagerPrincipalDetails principalDetails) {
        return JWT.create()
                .withSubject(JwtTemplate.TOKEN_PREFIX_MANAGER)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtTemplate.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getManager().getId())
                .withClaim("username", principalDetails.getManager().getUsername())
                .sign(Algorithm.HMAC256(JwtTemplate.SECRET));
    }

    public String getUsernameFromToken(String token) {
        if (token.startsWith("U")) {
            token = token.replace(JwtTemplate.TOKEN_PREFIX_USER, "");
        }
        else if (token.startsWith("M")) {
            token = token.replace(JwtTemplate.TOKEN_PREFIX_MANAGER, "");
        }
        String username = JWT.require(Algorithm.HMAC256(JwtTemplate.SECRET)).build().verify(token).getClaim("username").asString();
        return username;
    }

    // 만료되었는지
    private boolean isTokenExpired(String token) {
        Date exp = JWT.require(Algorithm.HMAC256(JwtTemplate.SECRET)).build().verify(token).getClaim("exp").asDate();
        return exp.before(new Date());
    }
}