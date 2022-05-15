package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.entity.Operation;
import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class CashDto {

    private Long id;

    @NotNull
    @Valid
    private Organization organization;

    @NotEmpty
    @Size(max = 30, message = "длина названия организации превышает лимит")
    private String name;

    @Min(value = 0, message = "Значение должно быть положительным")
    private Long money;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "поле даты создания не должно быть пустым")
    @Past(message = "неверный формат DateTime")
    private OffsetDateTime dateTimeCreated;

    @Valid
    private Collection<Operation> operations = new ArrayList<>();

    @Valid
    private Collection<User> allowUsers = new ArrayList<>();
}
