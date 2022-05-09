package com.rodkot.security.backend.repository;

import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.entity.Operation;
import com.rodkot.security.backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CashRepo extends JpaRepository<Cash, Long> {
    @Query(
            value = "SELECT * FROM cash WHERE organization_id = :organization_id",
            nativeQuery = true
    )
    List<Cash> getCashByOrganizationId(@Param("organization_id") Long id);

    @Query(
            value = "select * from cash as c,cash_allow_users as u where u.allow_users_id=:user_id and c.id=u.cash_id",
            nativeQuery = true
    )
    List<Cash> getByAllowUser(@Param("user_id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
            value = "INSERT INTO cash_allow_users (cash_id,allow_users_id) VALUES (:cash_id,:user_id)",
            nativeQuery = true
    )
    void putAllowUserInCash(@Param("cash_id") Long cashId, @Param("user_id") Long userId);

    //void addOperation(Long idCash, Operation operation);
}
