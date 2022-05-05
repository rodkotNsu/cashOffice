package com.rodkot.security.backend.repository;

import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepo extends JpaRepository<Cash,Long> {
}
