package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.entity.Role;
import com.rodkot.security.backend.entity.User;
import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.mapper.UserMapper;
import com.rodkot.security.backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final Role ROLE = new Role(null, "ROLE_ADMIN");
    private static final String EMAIL1 = "email@gmail.com";
    private static final String EMAIL2= "email2@gmail.com";
    private static final String FULL_NAME = "fullName";

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    private UserDto userDTO;


    @BeforeEach
    private void initializeData() {
        userDTO = new UserDto(null, LOGIN, PASSWORD, List.of(ROLE), EMAIL1, FULL_NAME);

    }

    @Test
    @DisplayName("Тест создания пользователя")
    void createUserSuccessful() {
        User savedUser = new User(1L, LOGIN, PASSWORD, List.of(ROLE), EMAIL1, FULL_NAME);
        when(userService.saveUser(userDTO)).thenReturn(savedUser);

        UserDto expectedUser = userMapper.userToUserDto(savedUser);
        Response<UserDto> expectedResponse = Response.withData(expectedUser);

        Response<UserDto> actualResponse = userController.create(userDTO);

        assertThat(actualResponse)
                .usingRecursiveComparison()
                .isEqualTo(expectedResponse);
    }

    @Disabled
    @Test
    @DisplayName("Тест успешного удаления пользователя")
    void deleteUserSuccessful() {
        doNothing().when(userService).deleteUser(any());
        Response<HttpStatus> expectedResponse = Response.withData(HttpStatus.OK);
    }

    @Test
    @DisplayName("Тест успешного обновления пользователя")
    void updateUserSuccessful() {
        User savedUser = new User(1L, LOGIN, PASSWORD, List.of(ROLE), EMAIL1, FULL_NAME);
        lenient().when(userService.updateUser(1L,userDTO)).thenReturn(savedUser);
        UserDto expectedUser = userMapper.userToUserDto(savedUser);
        Response<UserDto> expectedResponse = Response.withData(expectedUser);

        Response<UserDto> actualResponse = userController.create(userDTO);

        assertThat(actualResponse)
                .usingRecursiveComparison()
                .isEqualTo(expectedResponse);
    }
    @Disabled
    @Test
    @DisplayName("Тест выброса исключения при удалении пользователя")
    void deleteUserFailed() {
         Response<Void> actualResponse = userController.delete(any());

    }

}