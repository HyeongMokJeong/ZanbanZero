package com.hanbat.zanbanzero.repository.user;

import com.hanbat.zanbanzero.Entity.user.User;
import com.hanbat.zanbanzero.dto.info.UserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value =
            "SELECT EXISTS(" +
                    "SELECT * " +
                    "FROM user " +
                    "WHERE username = :username)",
        nativeQuery = true)
    Long doubleCheckUsername(@Param("username") String username);

    User findByUsername(@Param("username") String username);

}
