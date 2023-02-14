package com.hanbat.zanbanzero.service.store;

import com.hanbat.zanbanzero.Entity.store.Store;
import com.hanbat.zanbanzero.dto.store.StoreDto;
import com.hanbat.zanbanzero.repository.store.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StoreServiceTest {

    @Autowired
    StoreRepository storeRepository;

    @Test
    void getAllStoresList() {
        List<Store> result = storeRepository.findAll();
        result.forEach((r) -> {
            System.out.println(r.toString());
        });
    }

    @Test
    void getStoresToLocation() {
        Long id = 2L;
        List<Store> result = storeRepository.findByLocation(id);

//        Store b = new Store(2L, "test store 2", 2);
//        Store c = new Store(3L, "test store 3", 2);
//        List<Store> expected = Arrays.asList(b, c);

//        assertEquals(result.toString(), expected.toString());
    }

    @Test
    void getStoresToStoreId() {
        {
            Long testId = 1L;
            Store result = storeRepository.findById(testId).orElse(null);

//            Store expected = new Store(1L, "test store 1", 1);

//            assertEquals(expected.toString(), result.toString());
        }
    }

    @Test
    void getStoresByManagerId() {
        Long testId = 3L;
        List<Store> result = storeRepository.findByManagerId(testId);
        System.out.println(result.stream()
                .map(store -> StoreDto.createStoreDto(store))
                .collect(Collectors.toList()));
    }
}