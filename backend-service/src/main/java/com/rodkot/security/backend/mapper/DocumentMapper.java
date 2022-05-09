package com.rodkot.security.backend.mapper;

import com.rodkot.security.backend.dto.DocumentDto;
import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.entity.Document;
import com.rodkot.security.backend.entity.Operation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document documentDtoToDocument(DocumentDto documentDto);

    DocumentDto documentToDocumentDto(Document document);
}
