package com.rodkot.security.backend.repository;

import com.rodkot.security.backend.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepo extends JpaRepository<Operation,Long> {
}
