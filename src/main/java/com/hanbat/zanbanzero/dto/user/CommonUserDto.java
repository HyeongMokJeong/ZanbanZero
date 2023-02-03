package com.hanbat.zanbanzero.dto.user;

import com.hanbat.zanbanzero.Entity.user.CommonUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonUserDto {
    private Long id;
    private String userId;
    private String password;
    private String nickname;

    public CommonUserDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public static CommonUserDto createCommonUserDto(CommonUser entity) {
        return new CommonUserDto(
                entity.getId(),
                entity.getUserId(),
                entity.getPassword(),
                entity.getNickname()
        );
    }
}
