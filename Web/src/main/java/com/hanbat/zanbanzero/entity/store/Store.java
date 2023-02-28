//package com.hanbat.zanbanzero.Entity.store;
//
//import com.hanbat.zanbanzero.Entity.user.Manager;
//import com.hanbat.zanbanzero.dto.store.StoreDto;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class Store {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private int location;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "managers_id")
//    private Manager manager;
//
//    public Store(Long storeId) {
//        id = storeId;
//    }
//
//    public static Store createStore(StoreDto dto, Manager manager) {
//        return new Store(
//                dto.getId(),
//                dto.getName(),
//                dto.getLocation(),
//                manager
//        );
//    }
//}
