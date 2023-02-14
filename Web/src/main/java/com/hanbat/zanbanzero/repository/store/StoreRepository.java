package com.hanbat.zanbanzero.repository.store;

import com.hanbat.zanbanzero.Entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value =
            "SELECT EXISTS(" +
                    "SELECT * " +
                    "FROM store " +
                    "WHERE name = :name " +
                    "AND manager_id = :id)",
            nativeQuery = true)
    Long doubleCheckStoreName(@Param("name") String name, @Param("id") Long id);

    List<Store> findByLocation(Long id);

    List<Store> findByManagerId(Long id);
}
