package com.rodkot.security.backend.mapper;

import com.rodkot.security.backend.dto.CashDto;
import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.entity.Operation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    Operation operationDtoToOperation(OperationDto operationDto);

    OperationDto operationToOperationDto(Operation operation);
}
