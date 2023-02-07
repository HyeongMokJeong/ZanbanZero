package com.hanbat.zanbanzero.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hanbat.zanbanzero.auth.PrincipalDetails;
import com.hanbat.zanbanzero.dto.user.UserDto;
import com.hanbat.zanbanzero.template.JwtTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    public static String createToken(PrincipalDetails principalDetails) {
        return JWT.create()
                .withSubject(JwtTemplate.TOKEN_PREFIX)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtTemplate.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC256(JwtTemplate.SECRET));
    }

    // 유효 여부 확인
    public Boolean isValidToken(String token, UserDto userDto) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDto.getUsername()) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        token = token.replace(JwtTemplate.TOKEN_PREFIX, "");
        String username = JWT.require(Algorithm.HMAC256(JwtTemplate.SECRET)).build().verify(token).getClaim("username").asString();
        return username;
    }
    
    // 만료되었는지
    private boolean isTokenExpired(String token) {
        Date exp = JWT.require(Algorithm.HMAC256(JwtTemplate.SECRET)).build().verify(token).getClaim("exp").asDate();
        return exp.before(new Date());
    }
}