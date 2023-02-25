package com.hanbat.zanbanzero.dto.store;

import com.hanbat.zanbanzero.Entity.store.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private Long id;
    private String name;
    private Integer cost;
    private String info;
    private String image;

    public static MenuDto createMenuDto(Menu menu) {
        return new MenuDto(
                menu.getId(),
                menu.getName(),
                menu.getCost(),
                menu.getInfo(),
                menu.getImage()
        );
    }
}
