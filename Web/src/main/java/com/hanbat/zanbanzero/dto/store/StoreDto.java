package com.hanbat.zanbanzero.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Long id;
    private String name;
    private int location;
}
