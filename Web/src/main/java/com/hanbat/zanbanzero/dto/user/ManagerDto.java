package com.hanbat.zanbanzero.dto.user;

import com.hanbat.zanbanzero.Entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String roles;
    private Long store_id;
}
