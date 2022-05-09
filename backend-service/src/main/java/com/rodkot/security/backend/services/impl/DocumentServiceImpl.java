package com.rodkot.security.backend.services.impl;

import com.rodkot.security.backend.dto.DocumentDto;
import com.rodkot.security.backend.entity.Document;
import com.rodkot.security.backend.exception.IODocumentException;
import com.rodkot.security.backend.mapper.DocumentMapper;
import com.rodkot.security.backend.repository.DocumentRepo;
import com.rodkot.security.backend.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final Path root = Paths.get("uploads");
    private final DocumentRepo documentRepo;
    private final DocumentMapper documentMapper;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new IODocumentException("Невозможно создать папку для хранения");
        }
    }

    @Override
    public void save(DocumentDto documentDto, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Document document = documentMapper.documentDtoToDocument(documentDto);
        try {
            file.transferTo(new File(root + fileName));
            document.setPath(fileName);
            documentRepo.save(document);
        } catch (IOException e) {
            throw new IODocumentException("Ошибка в сохранении документа!!!");
        }
    }

    @Deprecated
    @Override
    public UrlResource load(Long idDocument) {
        Document document = documentRepo.findById(idDocument).orElseThrow(() -> new RuntimeException("Не существует файла"));
        try {
            Path file = root.resolve(document.getPath());
            UrlResource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new IODocumentException("Невозможно прочитать файл!!!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteDocumentById(Long idDocument) {
        Document document = documentRepo.findById(idDocument).orElseThrow(() -> new RuntimeException("Не существует файла"));
        try {
            FileSystemUtils.deleteRecursively(root.resolve(document.getPath()));
            documentRepo.delete(document);
        } catch (IOException e) {
            throw new IODocumentException("Ошибка удаления файла");
        }
    }

    @Override
    public List<DocumentDto> getAll() {
        List<Document> documents = documentRepo.findAll();
        List<DocumentDto> documentDtos = new ArrayList<>();
        for (Document document : documents) {
            documentDtos.add(documentMapper.documentToDocumentDto(document));
        }
        return documentDtos;
    }

}
