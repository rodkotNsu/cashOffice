package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.entity.Role;
import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.dto.RoleDto;
import com.rodkot.security.backend.mapper.RoleMapper;
import com.rodkot.security.backend.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Tag(name = "Роли", description = "Запросы для взаимодействия с экземплярами ролей")
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping(value = "/all")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Получение ролей")
    public Response<List<RoleDto>> getAllCash() {
        return Response.withData(roleService.getAll());
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Возвращает роль по заданному id")
    public Response<RoleDto> getById(
            @Parameter(description = "Идентификатор необходимой роли")
            @PathVariable Long id) {

        return Response.withData(roleService.getById(id));
    }

    @PostMapping("/create")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Создает роль")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект новой роли")
    public Response<RoleDto> create(@Valid @RequestBody RoleDto roleDto) {
        Role role = roleService.addRole(roleDto);
        return Response.withData(roleMapper.roleToRoleDto(role));
    }

    @PostMapping("/{id}/update")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Обновляет роль")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект обновленной роли")
    public Response<Void> update(@Valid @RequestBody RoleDto roleDto,
                                 @Parameter(description = "Идентификатор необходимой роли")
                                 @PathVariable Long id) {

        roleService.updateRole(id, roleDto);
        return Response.withoutErrors();
    }

    @Deprecated
    @DeleteMapping("/{id}/delete")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Удаляет лицензию")
    public Response<Void> delete(@Parameter(description = "id удаляемой кассы")
                                 @PathVariable Long id) {

        roleService.removeById(id);
        return Response.withoutErrors();
    }
}
