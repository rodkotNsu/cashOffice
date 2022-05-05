package com.rodkot.security.backend.mapper;

import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.dto.OrganizationDto;
import com.rodkot.security.backend.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    public OrganizationMapper INSTANCE = Mappers.getMapper( OrganizationMapper.class );
    Organization organizationDtoToOrganization(OrganizationDto organizationDto);

    OrganizationDto organizationToOrganizationDto(Organization organization);
}
