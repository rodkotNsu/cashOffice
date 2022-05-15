package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty
    @Size(max = 30  , message = "длина логина пользователя превышает лимит")
    private String username;

    @NotEmpty
    @Size(max = 30, message = "пароль превышает лимит символов")
    private String password;

    @NotNull
    private Collection<RoleDto> roles= new ArrayList<>();

    @NotEmpty(message = "электронная почта не может быть пустой")
    @Size(max = 30, message = "электронная почта превышает лимит символов")
    @Email(message = "неверный адрес электронной почты")
    private String email;

    @NotEmpty
    @Size(max = 30, message = "полное имя превышает лимит символов")
    @Pattern(regexp = "^[A-Za-z]+$",message = "полное имя содержит недопустимые символы")
    private String fullName;
}
