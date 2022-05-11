package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.dto.DocumentDto;
import com.rodkot.security.backend.services.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Документы", description = "Запросы для взаимодействия с документами")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/upload")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Загружает новый документ")
    public Response<Void> uploadFile(@RequestBody DocumentDto documentDto, @RequestParam("file") MultipartFile file) {
        documentService.save(documentDto, file);
        return Response.withoutErrors();
    }

    @GetMapping("/files")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Возвращает список всех документов")
    public Response<List<DocumentDto>> getListFiles() {
        List<DocumentDto> documentDtos = documentService.getAll();
        return Response.withData(documentDtos);
    }

    @GetMapping("/files/{id}/upload/")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Скачивает файл")
    public Response<UrlResource> uploadFile(@Parameter(description = "Идентификатор документа для скачивания")
                                            @PathVariable Long id) {
        UrlResource file = documentService.load(id);
        return Response.withData(file);
    }
}
