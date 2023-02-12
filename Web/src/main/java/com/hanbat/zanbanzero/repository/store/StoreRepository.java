package com.hanbat.zanbanzero.repository.store;

import com.hanbat.zanbanzero.Entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByLocation(Long id);
}
