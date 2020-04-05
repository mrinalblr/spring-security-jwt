package com.stackfortech.springsecurityjwt.repository;

import com.stackfortech.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User user set user.password = ?1 where user.id = ?2")
    Integer updatePassword(String password,Long id);
}
