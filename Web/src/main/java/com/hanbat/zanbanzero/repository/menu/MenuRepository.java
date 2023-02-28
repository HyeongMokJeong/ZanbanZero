package com.hanbat.zanbanzero.repository.menu;

import com.hanbat.zanbanzero.entity.store.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value =
            "SELECT EXISTS(" +
                    "SELECT * " +
                    "FROM menu " +
                    "WHERE name = :name )",
            nativeQuery = true)
    Long doubleCheckMenuName(@Param("name") String name);

    Menu findByName(@Param("name") String name);
}
