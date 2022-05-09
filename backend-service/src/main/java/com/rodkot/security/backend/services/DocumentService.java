package com.rodkot.security.backend.services;

import com.rodkot.security.backend.dto.DocumentDto;
import io.github.classgraph.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface DocumentService {
    public void init();
    public void save(DocumentDto documentDto,MultipartFile file);
    public UrlResource load(Long idDocument);
    public void deleteDocumentById(Long idDocument);
    public List<DocumentDto> getAll();
}
