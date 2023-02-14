package com.hanbat.zanbanzero.dto.store;

import com.hanbat.zanbanzero.Entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Long id;
    private String name;
    private int location;
    private Long manager_id;

    public static StoreDto createStoreDto(Store store) {
        return new StoreDto(
                store.getId(),
                store.getName(),
                store.getLocation(),
                store.getManager().getId()
        );
    }
}
