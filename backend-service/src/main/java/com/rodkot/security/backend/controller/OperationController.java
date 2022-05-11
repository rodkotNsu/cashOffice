package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.mapper.OperationMapper;
import com.rodkot.security.backend.services.OperationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/operation")
@Tag(name = "Операции", description = "Запросы для взаимодействия с экземплярами операций")
public class OperationController {
    private final OperationService operationService;
    private final OperationMapper operationMapper;

    @GetMapping("/all")
    @Operation(summary = "Возвращает все существующие операции")
    @ApiResponse(responseCode = "200")
    public Response<List<OperationDto>> getAll() {
        return Response.withData(operationService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Возвращает операцию по заданному id")
    @ApiResponse(responseCode = "200")
    public Response<OperationDto> getById(@Parameter(description = "Идентификатор необходимой операции")
                                          @PathVariable Long id) {
        return Response.withData(operationService.getById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Создает Операцию")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект новой организации")
    public Response<OperationDto> create(@RequestBody OperationDto operationDto) {
        com.rodkot.security.backend.entity.Operation operation = operationService.addOperation(operationDto);
        return Response.withData(operationMapper.operationToOperationDto(operation));
    }

    @PostMapping("/{id}/add")
    @Operation(summary = "Создает операцию и выполняет ее над кассой")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект новой организации")
    public Response<Void> addOperationInCash(@RequestBody OperationDto operationDto,
                                             @Parameter(description = "Идентификатор кассы, в которой добавляется операция")
                                             @PathVariable Long id) {
        operationService.addOperationInCash(id, operationDto);
        return Response.withoutErrors();
    }

    @PostMapping("/{id}/update")
    @Operation(summary = "Обновляет организацию")
    @ApiResponse(responseCode = "200")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект обновленной организации")
    public Response<Void> update(@Parameter(description = "id обновляемой операции") @PathVariable Long id,
                                 @RequestBody OperationDto operationDto) {
        operationService.updateOperation(id, operationDto);
        return Response.withoutErrors();
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Удаляет организацию")
    @ApiResponse(responseCode = "200")
    public Response<Void> delete(@Parameter(description = "id удаляемой организации")
                                 @PathVariable Long id) {
        operationService.removeById(id);
        return Response.withoutErrors();
    }
}
