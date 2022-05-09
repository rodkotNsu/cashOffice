package com.rodkot.security.backend.services;

import com.rodkot.security.backend.dto.RoleDto;
import com.rodkot.security.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleService {
    void saveRole(RoleDto roleDto);
    List<RoleDto> getAll();

    RoleDto getById(Long idRole);

    void addRole(RoleDto roleDto);

    void removeById(Long idRole);

    void updateRole(Long idRole, RoleDto roleDto);
}
