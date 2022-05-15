package com.rodkot.security.backend.services.impl;

import com.rodkot.security.backend.dto.RoleDto;
import com.rodkot.security.backend.entity.Role;
import com.rodkot.security.backend.exception.BadRequestException;
import com.rodkot.security.backend.exception.NotFoundException;
import com.rodkot.security.backend.mapper.RoleMapper;
import com.rodkot.security.backend.repository.RoleRepo;
import com.rodkot.security.backend.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;
    private final RoleMapper roleMapper;

    @Override
    public Role saveRole(RoleDto roleDto) {
        try {
            return roleRepo.save(roleMapper.roleDtoToRole(roleDto));
        }catch (DataIntegrityViolationException e){
            throw new BadRequestException("Уже есть роль с таким названием");
        }

    }

    @Override
    public List<RoleDto> getAll() {
        List<Role> roles = roleRepo.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(roleMapper.roleToRoleDto(role));
        }
        return roleDtos;
    }

    @Override
    public RoleDto getById(Long idRole) {
        return roleMapper.roleToRoleDto(roleRepo.findById(idRole).orElseThrow(() -> new BadRequestException("Касса не найдена")));
    }

    @Override
    public Role addRole(RoleDto roleDto) {
        return saveRole(roleDto);
    }

    @Override
    public void removeById(Long idRole) {
        roleRepo.deleteById(idRole);
    }

    @Override
    public void updateRole(Long idRole, RoleDto roleDto) {
        Role role = roleMapper.roleDtoToRole(roleDto);
        role.setId(idRole);
        roleRepo.save(role);
    }
}
