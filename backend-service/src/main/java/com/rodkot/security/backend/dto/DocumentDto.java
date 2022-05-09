package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.operation.TypeDocument;

import java.time.OffsetDateTime;

public class DocumentDto {
    private Long id;
    private TypeDocument typeDocument;
    private String name;
    private String path;
    private OffsetDateTime createDataTime;
}
