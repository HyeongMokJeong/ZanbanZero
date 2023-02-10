package com.hanbat.zanbanzero.Entity.user;

import com.hanbat.zanbanzero.dto.user.ManagerDto;
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
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String roles;

    public static Manager createAdminUser(ManagerDto dto) {
        return new Manager(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getNickname(),
                dto.getRoles()
        );
    }
}
