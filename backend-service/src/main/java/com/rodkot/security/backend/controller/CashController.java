package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.Response;
import com.rodkot.security.backend.dto.CashDto;
import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.services.CashService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cash")
@AllArgsConstructor
public class CashController {
    private final CashService cashService;

    @GetMapping(value = "/all")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Получение всех касс")
    public Response<List<CashDto>> getAllCash(){

        return Response.withData(cashService.getAll());
    }

    @PostMapping("/{id}")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Возвращает кассу по заданному id")
    public Response<CashDto> getById(
            @Parameter(description = "Идентификатор необходимой кассы")
            @PathVariable Long id) {

        return Response.withData(cashService.getById(id));
    }

    @PostMapping("/create")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Создает кассу")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Объект новой кассы")
    public void create(@RequestBody CashDto cash) {

        cashService.addCash(cash);
    }

    @PostMapping("/{id}/delete")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Удаляет лицензию")
    public void delete(@Parameter(description = "id удаляемой кассы")
                       @PathVariable Long id) {

        cashService.removeById(id);
    }
    @PostMapping("/organization/{id}")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Возвращает кассы заданной организации")
    public Response<List<CashDto>> getByOrganization(@Parameter(description = "Идентификатор организации, по которому ищутся кассы")
                                                     @PathVariable Long id) {

        return Response.withData(cashService.getByOrganization(id));
    }
    @PostMapping("/user/allow/{id}")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Возвращает кассы доступные пользователю")
    public Response<List<CashDto>> getByAllowUser(@Parameter(description = "Идентификатор пользователя, по которому ищутся кассы")
                                                     @PathVariable Long id) {

        return Response.withData(cashService.getByAllowUser(id));
    }


}
