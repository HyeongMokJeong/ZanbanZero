package com.hanbat.zanbanzero.service.store;

import com.hanbat.zanbanzero.Entity.store.Store;
import com.hanbat.zanbanzero.Entity.user.Manager;
import com.hanbat.zanbanzero.dto.store.StoreDto;
import com.hanbat.zanbanzero.exception.controller.exceptions.CantFindByIdException;
import com.hanbat.zanbanzero.exception.controller.exceptions.JwtException;
import com.hanbat.zanbanzero.exception.controller.exceptions.RequestDataisNull;
import com.hanbat.zanbanzero.exception.controller.exceptions.SameNameException;
import com.hanbat.zanbanzero.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<StoreDto> getAllStoresList() {
        List<Store> stores = storeRepository.findAll();
        List<StoreDto> result = new ArrayList<>();
        stores.forEach((store) -> {
            result.add(StoreDto.createStoreDto(store));
        });
        return result;
    }

    public StoreDto getStoresToStoreId(Long id) {
        Store store = storeRepository.findById(id).orElse(null);
        return (store != null) ?
                StoreDto.createStoreDto(store) :
                null;
    }

    public List<StoreDto> getStoresToLocation(Long id) {
        List<Store> stores = storeRepository.findByLocation(id);
        List<StoreDto> result = new ArrayList<>();
        stores.forEach((store) -> {
            result.add(StoreDto.createStoreDto(store));
        });
        return result;
    }

    public List<StoreDto> getStoresByManagerId(Long id) {
        List<Store> stores = storeRepository.findByManagerId(id);
        return stores.stream()
                .map(store -> StoreDto.createStoreDto(store))
                .collect(Collectors.toList());
    }

    public void addStore(StoreDto dto, Long id) throws CantFindByIdException, SameNameException, RequestDataisNull {
        if (storeRepository.doubleCheckStoreName(dto.getName(), id) == 1) {
            throw new SameNameException("데이터 중복입니다.");
        }

        if (dto.getName() == null) {
            throw new RequestDataisNull("데이터가 부족합니다. - " + "Name");
        }

        dto.setManager_id(id);
        Store store = Store.createStore(dto);
        try {
            storeRepository.save(store);
        }
        catch (DataIntegrityViolationException e) {
            throw new CantFindByIdException("잘못된 id 입니다.");
        }
    }
}
