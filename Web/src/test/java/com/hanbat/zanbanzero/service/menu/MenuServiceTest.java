package com.hanbat.zanbanzero.service.menu;

import com.hanbat.zanbanzero.Entity.store.Menu;
import com.hanbat.zanbanzero.Entity.store.Store;
import com.hanbat.zanbanzero.dto.store.MenuDto;
import com.hanbat.zanbanzero.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    MenuRepository menuRepository;

    @Test
    void getMenuToStoreId() {
        Long id = 1L;
        List<Menu> result = menuRepository.findByStoreId(id);

//        Menu a = new Menu(1L, new Store(1L, "test store 1", 1), "치킨너겟", 2000, "두유, 복숭아", null);
//        Menu b = new Menu(3L, new Store(1L, "test store 1", 1), "육회", 12345, "삼겹살 알레르기", null);
//        List<Menu> expected = Arrays.asList(a, b);
//
//        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void getMenuInfo() {
        Long id = 1L;
        Menu result = menuRepository.findById(id).orElse(null);
        MenuDto resultDto = MenuDto.createMenuDto(result);

//        MenuDto expected = new MenuDto(1L, 1L, "치킨너겟", 2000, "두유, 복숭아", null);
//
//        assertEquals(expected.toString(), resultDto.toString());
    }
}