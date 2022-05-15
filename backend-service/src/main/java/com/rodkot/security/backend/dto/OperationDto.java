package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.operation.TypeOperation;
import com.rodkot.security.backend.entity.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
public class OperationDto {
    private Long id;

    @NotNull(message = "у операции должен быть тип")
    private TypeOperation typeOperation;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "поле даты создания не должно быть пустым")
    @Past(message = "неверный формат DateTime")
    private OffsetDateTime dateTime;

    @Max(value = 100000,message = "Сумма операции превышает лимит")
    @Min(value = 0, message = "Значение должно быть положительным")
    private Long amount;

    @Valid
    private DocumentDto audio;

    @Valid
    private Collection<DocumentDto> documents = new ArrayList<>();
}
