package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
@Data
@AllArgsConstructor
public class OrganizationDto {
    private Long id;

    @NotEmpty
    @Size(max = 30  , message = "длина названия организации превышает лимит")
    private String name;

    @Valid
    @NotNull(message = "у организации должен быть собственник")
    private UserDto owner;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "поле даты создания не должно быть пустым")
    @Past(message = "неверный формат DateTime")
    private OffsetDateTime createDataTime;
}
