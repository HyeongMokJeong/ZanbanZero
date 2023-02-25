package com.hanbat.zanbanzero.service.menu;

import com.hanbat.zanbanzero.Entity.store.Menu;
import com.hanbat.zanbanzero.dto.store.MenuDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.exception.controller.exceptions.SameNameException;
import com.hanbat.zanbanzero.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuDto> getMenu() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuDto> result = new ArrayList<>();
        menus.forEach((menu) -> {
            result.add(MenuDto.createMenuDto(menu));
        });
        return result;
    }

    public MenuDto getMenuInfo(Long id) throws CantFindByIdException {
        Menu menu = menuRepository.findById(id).orElse(null);
        if (menu == null) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }
        return MenuDto.createMenuDto(menu);
    }

    public void addMenu(MenuDto dto) throws SameNameException, CantFindByIdException {
        if (menuRepository.doubleCheckMenuName(dto.getName()) == 1) {
            throw new SameNameException("데이터 중복입니다.");
        }
        Menu menu = Menu.createMenu(dto);

        try {
            menuRepository.save(menu);
        }
        catch (DataIntegrityViolationException e) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }
    }

    public void updateMenu(MenuDto dto, Long id) throws CantFindByIdException {
        Menu menu = menuRepository.findById(id).orElse(null);
        if (menu == null) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }

        menu.patch(dto);
        menuRepository.save(menu);
    }

    public void deleteMenu(Long id) throws CantFindByIdException {
        Menu menu = menuRepository.findById(id).orElse(null);
        if (menu == null) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }

        menuRepository.delete(menu);
    }
}
