package com.hanbat.zanbanzero.Entity.user;

import com.hanbat.zanbanzero.dto.user.UserDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String roles;
    private Long storeId;

    public static User createCommonUser(UserDto dto) {
        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getNickname(),
                dto.getRoles(),
                dto.getStoreId()
        );
    }
}
