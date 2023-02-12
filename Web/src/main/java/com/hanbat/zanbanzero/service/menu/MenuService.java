package com.hanbat.zanbanzero.service.menu;

import com.hanbat.zanbanzero.Entity.store.Menu;
import com.hanbat.zanbanzero.dto.store.MenuDto;
import com.hanbat.zanbanzero.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuDto> getMenuToStoreId(Long id) {
        List<Menu> menus = menuRepository.findByStoreId(id);
        List<MenuDto> result = new ArrayList<>();
        menus.forEach((menu) -> {
            result.add(MenuDto.createMenuDto(menu));
        });
        return result;
    }

    public MenuDto getMenuInfo(Long id) {
        Menu menu = menuRepository.findById(id).orElse(null);
        if (menu == null) {
            return null;
        }
        return MenuDto.createMenuDto(menu);
    }
}
