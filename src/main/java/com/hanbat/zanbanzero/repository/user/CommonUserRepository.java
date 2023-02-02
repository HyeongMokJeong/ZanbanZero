package com.hanbat.zanbanzero.repository.user;

import com.hanbat.zanbanzero.Entity.user.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommonUserRepository extends JpaRepository<CommonUser, Long> {

    @Query(value =
            "SELECT EXISTS(" +
                    "SELECT * " +
                    "FROM common_user " +
                    "WHERE user_id = :userId)",
        nativeQuery = true)
    boolean doubleCheckUserId(@Param("userId") String userId);

    @Query(value =
            "SELECT * " +
            "FROM common_user " +
            "WHERE user_id = :userId",
        nativeQuery = true)
    CommonUser findByUserId(@Param("userId") String userId);
}
