package com.rodkot.security.backend.services.impl;

import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.dto.OrganizationDto;
import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.entity.Operation;
import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.mapper.OperationMapper;
import com.rodkot.security.backend.mapper.OrganizationMapper;
import com.rodkot.security.backend.repository.OperationRepo;
import com.rodkot.security.backend.repository.OrganizationRepo;
import com.rodkot.security.backend.services.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepo organizationRepo;
    private final OrganizationMapper organizationMapper;

    @Override
    public List<OrganizationDto> getAll() {
        List<Organization> organizations = organizationRepo.findAll();
        List<OrganizationDto> organizationDtos= new ArrayList<>();
        for (Organization organization :organizations) {
            organizationDtos.add(organizationMapper.organizationToOrganizationDto(organization));
        }
        return organizationDtos;
    }

    @Override
    public OrganizationDto getById(Long id) {
        return null;
    }

    @Override
    public List<OrganizationDto> getByUser(Long id) {
        return null;
    }

    @Override
    public void addOrganization(OrganizationDto organizationDto) {
        Organization organization = organizationMapper.organizationDtoToOrganization(organizationDto);
        organizationRepo.save(organization);
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void updateOrganization(Long id, OrganizationDto organization) {

    }
}
