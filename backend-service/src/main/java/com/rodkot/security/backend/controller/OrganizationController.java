package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.dto.OrganizationDto;
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
    public void create(@RequestBody OrganizationDto organization) {
        organizationService.addOrganization(organization);
    }

    @PostMapping("/{id}/update")
    @Operation(summary = "Обновляет организацию")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект обновленной организации")
    public void update(@Parameter(description = "id обновляемой организации") @PathVariable Long id,
                       @RequestBody OrganizationDto organization) {
        organizationService.updateOrganization(id,organization);
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Удаляет организацию")
    @ApiResponse(responseCode = "200")
    public void delete(@Parameter(description = "id удаляемой организации")
                       @PathVariable Long id) {
        organizationService.removeById(id);
    }

}
