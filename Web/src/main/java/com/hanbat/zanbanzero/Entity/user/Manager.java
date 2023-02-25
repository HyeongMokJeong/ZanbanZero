package com.hanbat.zanbanzero.Entity.user;

import com.hanbat.zanbanzero.dto.user.ManagerDto;
import com.hanbat.zanbanzero.dto.user.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String roles;

    public static Manager createManager(ManagerDto dto) {
        return new Manager(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getRoles()
        );
    }
}
