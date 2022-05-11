package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.dto.OrganizationDto;
import com.rodkot.security.backend.mapper.OrganizationMapper;
import com.rodkot.security.backend.services.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
@AllArgsConstructor
@Tag(name = "Организации", description = "Запросы для взаимодействия с экземплярами организаций")
public class OrganizationController {
    OrganizationService organizationService;
    OrganizationMapper organizationMapper;

    @GetMapping("/all")
    @Operation(summary = "Возвращает все существующие организации")
    @ApiResponse(responseCode = "200")
    public Response<List<OrganizationDto>> getAll() {
        return Response.withData(organizationService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Возвращает организацию по заданному id")
    @ApiResponse(responseCode = "200")
    public Response<OrganizationDto> getById(@Parameter(description = "Идентификатор необходимой организации")
                                             @PathVariable Long id) {
        return Response.withData(organizationService.getById(id));
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Возвращает организации заданного пользователя")
    @ApiResponse(responseCode = "200")
    public Response<List<OrganizationDto>> getAllByClient(@Parameter(description = "Идентификатор пользователя, по которому ищутся организации")
                                                          @PathVariable Long id) {
        return Response.withData(organizationService.getAllByUser(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Создает организации")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект новой организации")
    public Response<OrganizationDto> create(@RequestBody OrganizationDto organizationDto) {
        Organization organizationSaved = organizationService.addOrganization(organizationDto);
        return Response.withData(organizationMapper.organizationToOrganizationDto(organizationSaved));
    }

    @PostMapping("/{id}/update")
    @Operation(summary = "Обновляет организацию")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект обновленной организации")
    public Response<Void> update(@Parameter(description = "id обновляемой организации") @PathVariable Long id,
                                 @RequestBody OrganizationDto organization) {
        organizationService.updateOrganization(id, organization);
        return Response.withoutErrors();
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Удаляет организацию")
    @ApiResponse(responseCode = "200")
    public Response<Void> delete(@Parameter(description = "id удаляемой организации")
                                 @PathVariable Long id) {
        organizationService.removeById(id);
        return Response.withoutErrors();
    }

}
