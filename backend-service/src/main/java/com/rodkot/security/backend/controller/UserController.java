package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "Пользователи", description = "Запросы для взаимодействия с экземплярами пользователей")
public class UserController {
    private final UserService userService;
    @GetMapping("/all")
    @Operation(summary = "Возвращает всех существующих пользователей")
    @ApiResponse(responseCode = "200")
    public Response<List<UserDto>> getAll() {

        return Response.withData(userService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Возвращает пользователя по идентификатору")
    @ApiResponse(responseCode = "200")
    public Response<UserDto> getById(@Parameter(description = "Идентификатор необходимого пользователя")
                                               @PathVariable Long id) {

        return Response.withData(userService.getUserById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Создает пользователя")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект нового пользователя")
    public void create(@RequestBody UserDto user) {
        userService.addUser(user);
    }

    @PostMapping("/{id}/update")
    @Operation(summary = "Обновляет пользователя")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект обновленного пользователя")
    public void update(@RequestBody UserDto user,
                       @Parameter(description = "id обновляемого пользователя")
                       @PathVariable Long id) {
        userService.updateUser(id,user);
    }
    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Удаляет пользователя")
    @ApiResponse(responseCode = "200")
    public void delete(@Parameter(description = "id удаляемого пользователя")
                       @PathVariable Long id) {
        userService.deleteUser(id);
    }
}
