package com.hanbat.zanbanzero.dto.user;

import com.hanbat.zanbanzero.Entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String roles;

    public UserDto(String username) {
        this.username = username;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static UserDto createCommonUserDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getNickname(),
                entity.getRoles()
        );
    }
}
