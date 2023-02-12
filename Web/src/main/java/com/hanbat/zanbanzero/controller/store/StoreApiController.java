package com.hanbat.zanbanzero.controller.store;

import com.hanbat.zanbanzero.Entity.store.Store;
import com.hanbat.zanbanzero.dto.store.StoreDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreApiController {

    private final StoreService storeService;

    @GetMapping("/api/user/stores")
    public ResponseEntity<List<StoreDto>> getAllStoresList() {
        List<StoreDto> storeDtos = storeService.getAllStoresList();
        return ResponseEntity.status(HttpStatus.OK).body(storeDtos);
    }

    @GetMapping("/api/user/store/location/{id}")
    public ResponseEntity<List<StoreDto>> getStoresToLocation(@PathVariable Long id) {
        List<StoreDto> storeDto = storeService.getStoresToLocation(id);
        return ResponseEntity.status(HttpStatus.OK).body(storeDto);
    }

    @GetMapping("/api/user/store/{id}")
    public ResponseEntity<StoreDto> getStoresToStoreId(@PathVariable Long id) throws CantFindByIdException {
        StoreDto storeDto = storeService.getStoresToStoreId(id);
        if (storeDto == null) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(storeDto);
    }
}
