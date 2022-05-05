package com.rodkot.security.backend.services;

import com.rodkot.security.backend.dto.OperationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperationService {
    List<OperationDto> getAll();

    OperationDto getById(Long id);

    void addOperation(OperationDto operationDto);
}
