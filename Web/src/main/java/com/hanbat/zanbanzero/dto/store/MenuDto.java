package com.hanbat.zanbanzero.dto.store;

import com.hanbat.zanbanzero.Entity.store.Menu;
import com.hanbat.zanbanzero.Entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private Long id;
    private Long storeId;
    private String name;
    private int cost;
    private String info;
    private String image;

    public static MenuDto createMenuDto(Menu menu) {
        return new MenuDto(
                menu.getId(),
                menu.getStore().getId(),
                menu.getName(),
                menu.getCost(),
                menu.getInfo(),
                menu.getImage()
        );
    }
}
