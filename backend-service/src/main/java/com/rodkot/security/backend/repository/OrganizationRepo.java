package com.rodkot.security.backend.repository;

import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization,Long> {
}
