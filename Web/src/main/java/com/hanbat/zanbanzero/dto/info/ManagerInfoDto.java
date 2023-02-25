package com.hanbat.zanbanzero.dto.info;

import com.hanbat.zanbanzero.Entity.user.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerInfoDto {
    private Long id;
    private String username;

    public static ManagerInfoDto createManagerInfoDto(Manager manager) {
        return new ManagerInfoDto(
                manager.getId(),
                manager.getUsername()
        );
    }
}
