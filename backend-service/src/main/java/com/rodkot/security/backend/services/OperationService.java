package com.rodkot.security.backend.services;

import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.entity.Operation;
import com.rodkot.security.backend.exception.InsufficientFundsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperationService {
    List<OperationDto> getAll();

    OperationDto getById(Long idOperation);

    Operation addOperation(OperationDto operationDto);

    void updateOperation(Long idOperation, OperationDto operationDto);

    void removeById(Long idOperation);

    void addOperationInCash(Long idCash, OperationDto operationDto) throws InsufficientFundsException;
}
