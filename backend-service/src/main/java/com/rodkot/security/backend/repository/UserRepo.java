package com.rodkot.security.backend.repository;


import com.rodkot.security.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
