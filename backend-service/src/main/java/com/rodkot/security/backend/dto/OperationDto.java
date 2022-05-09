package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.operation.TypeOperation;
import com.rodkot.security.backend.entity.Document;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class OperationDto {
    private Long id;
    private TypeOperation typeOperation;
    private OffsetDateTime dateTime;
    private Long amount;
    private Document audio;
    private Collection<Document> documents = new ArrayList<>();
}
