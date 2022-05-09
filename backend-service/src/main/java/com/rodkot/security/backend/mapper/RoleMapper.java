package com.rodkot.security.backend.mapper;

import com.rodkot.security.backend.dto.RoleDto;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.entity.Role;
import com.rodkot.security.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleDtoToRole(RoleDto roleDto);

    RoleDto roleToRoleDto(Role role);
}
