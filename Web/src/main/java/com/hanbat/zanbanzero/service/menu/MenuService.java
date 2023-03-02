package com.hanbat.zanbanzero.service.menu;

import com.hanbat.zanbanzero.entity.store.Menu;
import com.hanbat.zanbanzero.dto.store.MenuDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.exception.controller.exceptions.SameNameException;
import com.hanbat.zanbanzero.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuDto> getMenus() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuDto> result = new ArrayList<>();
        menus.forEach((menu) -> {
            result.add(MenuDto.createMenuDto(menu));
        });
        return result;
    }

    public MenuDto getMenuInfo(Long id) throws CantFindByIdException {
        Menu menu = menuRepository.findById(id).orElseThrow(CantFindByIdException::new);

        return MenuDto.createMenuDto(menu);
    }

    public void addMenu(MenuDto dto) throws SameNameException {
        if (menuRepository.doubleCheckMenuName(dto.getName()) == 1) {
            throw new SameNameException("데이터 중복입니다.");
        }
        Menu menu = Menu.createMenu(dto);

        menuRepository.save(menu);

    }

    public void updateMenu(MenuDto dto, Long id) throws CantFindByIdException {
        Menu menu = menuRepository.findById(id).orElseThrow(CantFindByIdException::new);

        menu.patch(dto);
        menuRepository.save(menu);
    }

    public void deleteMenu(Long id) throws CantFindByIdException {
        Menu menu = menuRepository.findById(id).orElseThrow(CantFindByIdException::new);

        menuRepository.delete(menu);
    }
}
