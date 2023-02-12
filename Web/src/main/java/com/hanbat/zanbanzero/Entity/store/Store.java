package com.hanbat.zanbanzero.Entity.store;

import com.hanbat.zanbanzero.dto.store.StoreDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int location;

    public Store(Long storeId) {
        id = storeId;
    }

    public static Store createStore(StoreDto dto) {
        return new Store(
                dto.getId(),
                dto.getName(),
                dto.getLocation()
        );
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
