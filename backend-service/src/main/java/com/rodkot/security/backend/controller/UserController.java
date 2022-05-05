package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.Response;
import com.rodkot.security.backend.dto.OrganizationDto;
import com.rodkot.security.backend.dto.RoleDto;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/all")
    @Operation(summary = "Возвращает всех существующих пользователей")
    @ApiResponse(responseCode = "200")
    public Response<List<UserDto>> getAll() {

        return Response.withData(userService.getAll());
    }
    @PostMapping("/role")
    @Operation(summary = "Создает роль")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект нового пользователя")
    public void createRole(@RequestBody RoleDto roleDto) {
        userService.addRole(roleDto);
    }
    @PostMapping("/create")
    @Operation(summary = "Создает пользователя")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект нового пользователя")
    public void create(@RequestBody UserDto user) {
        userService.addUser(user);
    }


}
