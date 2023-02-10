package com.hanbat.zanbanzero.dto.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private Long id;
    private Long store_id;
    private String name;
    private int cost;
    private String info;
    private String image;
}
