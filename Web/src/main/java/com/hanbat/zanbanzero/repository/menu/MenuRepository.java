package com.hanbat.zanbanzero.repository.menu;

import com.hanbat.zanbanzero.Entity.store.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByStoreId(Long id);

    @Query(value =
            "SELECT EXISTS(" +
                    "SELECT * " +
                    "FROM menu " +
                    "WHERE name = :name " +
                    "AND store_id = :id)",
            nativeQuery = true)
    Long doubleCheckMenuName(@Param("name") String name,@Param("id") Long id);
}
