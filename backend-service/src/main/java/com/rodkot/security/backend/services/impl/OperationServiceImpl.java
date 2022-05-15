package com.rodkot.security.backend.services.impl;

import com.rodkot.security.backend.dto.CashDto;
import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.entity.Operation;
import com.rodkot.security.backend.exception.InsufficientFundsException;
import com.rodkot.security.backend.exception.MethodArgumentNotUniqueException;
import com.rodkot.security.backend.mapper.OperationMapper;
import com.rodkot.security.backend.repository.CashRepo;
import com.rodkot.security.backend.repository.OperationRepo;
import com.rodkot.security.backend.services.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepo operationRepo;
    private final CashRepo cashRepo;
    private final OperationMapper operationMapper;

    @Override
    public List<OperationDto> getAll() {
        List<Operation> operations = operationRepo.findAll();
        List<OperationDto> operationDtos = new ArrayList<>();
        for (Operation operation : operations) {
            operationDtos.add(operationMapper.operationToOperationDto(operation));
        }
        return operationDtos;
    }

    @Override
    public OperationDto getById(Long id) {
        Operation operation = operationRepo.findById(id).orElse(null);
        return operationMapper.operationToOperationDto(operation);
    }

    @Override
    public Operation addOperation(OperationDto operationDto) {
        Operation operation = operationMapper.operationDtoToOperation(operationDto);
        try {
            return operationRepo.save(operation);
        } catch (DataIntegrityViolationException e) {
            throw new MethodArgumentNotUniqueException("В сервисе уже  касса с таким именем");
        }
    }

    @Override
    public void updateOperation(Long idOperation, OperationDto operationDto) {
        Operation operation = operationMapper.operationDtoToOperation(operationDto);
        try {
            operationRepo.save(operation);
        } catch (DataIntegrityViolationException e) {
            throw new MethodArgumentNotUniqueException("В сервисе уже есть касса с таким именем");
        }
    }

    @Override
    public void removeById(Long idOperation) {
        operationRepo.deleteById(idOperation);
    }

    @Override
    public void addOperationInCash(Long idCash, OperationDto operationDto) throws InsufficientFundsException {
        Cash cash = cashRepo.getOne(idCash);
        Operation operation = operationMapper.operationDtoToOperation(operationDto);
        cash.runOperation(operation);
        operationRepo.saveOperationInCash(idCash, operationDto.getId());
    }
}
