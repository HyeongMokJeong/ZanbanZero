package com.hanbat.zanbanzero.Entity.user;

import com.hanbat.zanbanzero.dto.user.CommonUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CommonUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String password;

    @Column
    private String nickname;

    public static CommonUser createCommonUser(CommonUserDto dto) {
        return new CommonUser(
                dto.getId(),
                dto.getUserId(),
                dto.getPassword(),
                dto.getNickname()
        );
    }
}
