package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.Response;
import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.dto.OrganizationDto;
import com.rodkot.security.backend.services.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor
@RestController("/operation")
public class OperationController {
  private final OperationService operationService;

  @PostMapping("/all")
  @Operation(summary = "Возвращает все существующие операции")
  @ApiResponse(responseCode = "200")
  public Response<List<OperationDto>> getAll() {
    return Response.withData(operationService.getAll());
  }

  @PostMapping("/{id}")
  @Operation(summary = "Возвращает операцию по заданному id")
  @ApiResponse(responseCode = "200")
  public Response<OperationDto> getById(@Parameter(description = "Идентификатор необходимой операции")
                                           @PathVariable Long id) {
    return Response.withData(operationService.getById(id));
  }
  @PostMapping("/user/{id}")
  @Operation(summary = "Возвращает организации заданного пользователя")
  @ApiResponse(responseCode = "200")
  public Response<List<OrganizationDto>> getByClient(@Parameter(description = "Идентификатор пользователя, по которому ищутся организации")
                                                     @PathVariable Long id) {
    return Response.withData(organizationService.getByUser(id));
  }

  @PostMapping("/create")
  @Operation(summary = "Создает организации")
  @ApiResponse(responseCode = "200")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект новой организации")
  public void create(@RequestBody OrganizationDto organization) {
    organizationService.addOrganization(organization);
  }

  @PostMapping("/{id}/update")
  @Operation(summary = "Обновляет организацию")
  @ApiResponse(responseCode = "200")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект обновленной организации")
  public void update(@Parameter(description = "id обновляемой организации") @PathVariable Long id,
                     @RequestBody OrganizationDto organization) {
    organizationService.updateOrganization(id,organization);
  }

  @PostMapping("/{id}/delete")
  @Operation(summary = "Удаляет организацию")
  @ApiResponse(responseCode = "200")
  public void delete(@Parameter(description = "id удаляемой организации")
                     @PathVariable Long id) {
    organizationService.removeById(id);
  }


}
