package com.rodkot.security.backend.repository;

import com.rodkot.security.backend.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface OperationRepo extends JpaRepository<Operation,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
            value = "INSERT INTO cash_operations (cash_id,operations_id) VALUES (:cash_id,:operation_id)",
            nativeQuery = true
    )
    void saveOperationInCash(@Param("cash_id") Long cashId,@Param("operation_id") Long operationID);
}
