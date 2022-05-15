package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.operation.TypeDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
@Data
@Builder
@AllArgsConstructor
public class DocumentDto {
    private Long id;
    
    @NotNull(message = "у документа должен быть тип")
    private TypeDocument typeDocument;

    @NotEmpty
    @Size(max = 30  , message = "длина названия организации превышает лимит")
    private String name;

    @NotEmpty
    @Size(max = 30  , message = "длина названия организации превышает лимит")
    private String path;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "поле даты создания не должно быть пустым")
    @Past(message = "неверный формат DateTime")
    private OffsetDateTime createDataTime;
}
