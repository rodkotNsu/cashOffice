package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.entity.User;
import com.rodkot.security.backend.exception.ErrorCode;
import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.mapper.UserMapper;
import com.rodkot.security.backend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "Пользователи", description = "Запросы для взаимодействия с экземплярами пользователей")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

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
        User user = userService.getUserById(id);
        UserDto userDto = userMapper.userToUserDto(user);
        return Response.withData(userDto);
    }

    @PostMapping("/create")
    @Operation(summary = "Создает пользователя")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект нового пользователя")
    public Response<UserDto> create(@Valid @RequestBody UserDto user) {
        User createUser = userService.saveUser(user);
        UserDto userDto = userMapper.userToUserDto(createUser);
        return Response.withData(userDto);
    }

    @PostMapping("/{id}/update")
    @Operation(summary = "Обновляет пользователя")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект обновленного пользователя")
    public Response<UserDto> update(@Valid @RequestBody UserDto user,
                                    @Parameter(description = "id обновляемого пользователя")
                                    @PathVariable Long id) {
        User userCreate = userService.updateUser(id, user);
        UserDto userDto = userMapper.userToUserDto(userCreate);
        return Response.withData(userDto);
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Удаляет пользователя")
    @ApiResponse(responseCode = "200")
    public Response<Void> delete(@Parameter(description = "id удаляемого пользователя")
                                 @PathVariable Long id) {
        userService.deleteUser(id);
        return Response.withoutErrors();
    }
}
