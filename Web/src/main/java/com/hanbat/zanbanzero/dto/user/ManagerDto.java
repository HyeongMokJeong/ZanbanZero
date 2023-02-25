package com.hanbat.zanbanzero.dto.user;

import com.hanbat.zanbanzero.Entity.user.Manager;
import com.hanbat.zanbanzero.Entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ManagerDto {
    private Long id;
    private String username;
    private String password;
    private String roles;

    public ManagerDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static ManagerDto createManagerDto(Manager entity) {
        return new ManagerDto(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRoles()
        );
    }
}
