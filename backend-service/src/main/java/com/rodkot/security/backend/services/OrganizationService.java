package com.rodkot.security.backend.services;

import com.rodkot.security.backend.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {
    List<OrganizationDto> getAll();

    OrganizationDto getById(Long id);

    List<OrganizationDto> getAllByUser(Long id);

    void addOrganization(OrganizationDto organization);

    void removeById(Long id);

    void updateOrganization(Long id, OrganizationDto organization);
}
