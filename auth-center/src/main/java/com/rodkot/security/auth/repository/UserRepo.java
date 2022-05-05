package com.rodkot.security.auth.repository;

import com.rodkot.security.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
