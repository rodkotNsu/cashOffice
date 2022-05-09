package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.dto.RoleDto;
import com.rodkot.security.backend.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Tag(name = "Роли", description = "Запросы для взаимодействия с экземплярами ролей")
public class RoleController {
    private final RoleService roleService;

    @GetMapping(value = "/all")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Получение ролей")
    public Response<List<RoleDto>> getAllCash(){
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
    public void create(@RequestBody RoleDto roleDto) {

        roleService.addRole(roleDto);
    }
    @PostMapping("/{id}/update")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Обновляет роль")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект обновленной роли")
    public void update(@RequestBody RoleDto roleDto,
                       @Parameter(description = "Идентификатор необходимой роли")
                       @PathVariable Long id) {

        roleService.updateRole(id,roleDto);
    }
    @Deprecated
    @DeleteMapping("/{id}/delete")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Удаляет лицензию")
    public void delete(@Parameter(description = "id удаляемой кассы")
                       @PathVariable Long id) {

        roleService.removeById(id);
    }
}
