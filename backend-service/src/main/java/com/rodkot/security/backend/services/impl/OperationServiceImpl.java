package com.rodkot.security.backend.services.impl;

import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.dto.OrganizationDto;
import com.rodkot.security.backend.entity.Operation;
import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.mapper.OperationMapper;
import com.rodkot.security.backend.mapper.OrganizationMapper;
import com.rodkot.security.backend.repository.OperationRepo;
import com.rodkot.security.backend.repository.OrganizationRepo;
import com.rodkot.security.backend.services.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepo operationRepo;
    private final OperationMapper operationMapper;
    @Override
    public List<OperationDto> getAll() {
        List<Operation> operations = operationRepo.findAll();
        List<OperationDto> operationDtos= new ArrayList<>();
        for (Operation operation :operations) {
            operationDtos.add(operationMapper.operationToOperationDto(operation));
        }
        return operationDtos;
    }

    @Override
    public OperationDto getById(Long id) {
        return null;
    }

    @Override
    public void addOperation(OperationDto operationDto) {
        Operation operation = operationMapper.operationDtoToOperation(operationDto);
        operationRepo.save(operation);
    }
}
