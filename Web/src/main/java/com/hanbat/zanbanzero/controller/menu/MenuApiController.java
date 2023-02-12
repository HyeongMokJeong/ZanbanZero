package com.hanbat.zanbanzero.controller.menu;

import com.hanbat.zanbanzero.dto.store.MenuDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuApiController {

    private final MenuService menuService;

    @GetMapping("/api/user/store/{id}/menu")
    public ResponseEntity<List<MenuDto>> getMenuToStoreId(@PathVariable Long id) {
        List<MenuDto> menus = menuService.getMenuToStoreId(id);
        return ResponseEntity.status(HttpStatus.OK).body(menus);
    }

    @GetMapping("/api/user/menu/{id}")
    public ResponseEntity<MenuDto> getMenuInfo(@PathVariable Long id) throws CantFindByIdException {
        MenuDto menuDto = menuService.getMenuInfo(id);
        if (menuDto == null) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(menuDto);
    }
}
