package com.hanbat.zanbanzero.Entity.user;

import com.hanbat.zanbanzero.Entity.store.Store;
import com.hanbat.zanbanzero.dto.user.AdminUserDto;
import com.hanbat.zanbanzero.dto.user.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String roles;

    @OneToOne
    @MapsId
    private Store store;

    public static AdminUser createAdminUser(AdminUserDto dto) {
        return new AdminUser(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getNickname(),
                dto.getRoles(),
                dto.getStore()
        );
    }
}
