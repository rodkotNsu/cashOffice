package com.rodkot.security.backend.repository;

import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepo extends JpaRepository<Organization,Long> {
    @Query(
            value = "SELECT * FROM organization WHERE organization_id = :user_id)",
            nativeQuery = true
    )
    List<Organization> getOrganizationByUserId(@Param("user_id") Long id);
}
