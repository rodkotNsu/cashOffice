package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.dto.CashDto;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.mapper.CashMapper;
import com.rodkot.security.backend.services.CashService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cash")
@AllArgsConstructor
@Tag(name = "Кассы", description = "Запросы для взаимодействия с экземплярами касс")
public class CashController {
    private final CashService cashService;
    private final CashMapper cashMapper;

    @GetMapping(value = "/all")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Получение всех касс")
    public Response<List<CashDto>> getAllCash() {

        return Response.withData(cashService.getAll());
    }

    @GetMapping("/{id}")
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
    public Response<CashDto> create(@RequestBody CashDto cash) {

        Cash cashSaved = cashService.addCash(cash);
        return Response.withData(cashMapper.cashToCashDto(cashSaved));
    }

    @DeleteMapping("/{id}/delete")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Удаляет кассу")
    public Response<Void> delete(@Parameter(description = "id удаляемой кассы")
                                 @PathVariable Long id) {

        cashService.removeById(id);
        return Response.withoutErrors();
    }

    @GetMapping("/organization/{id}")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Возвращает кассы заданной организации")
    public Response<List<CashDto>> getByOrganization(@Parameter(description = "Идентификатор организации, по которому ищутся кассы")
                                                     @PathVariable Long id) {

        return Response.withData(cashService.getByOrganization(id));
    }

    @GetMapping("/user/allow/get/{id}")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Возвращает кассы доступные пользователю")
    public Response<List<CashDto>> getByAllowUser(@Parameter(description = "Идентификатор пользователя, по которому ищутся кассы")
                                                  @PathVariable Long id) {

        return Response.withData(cashService.getByAllowUser(id));
    }

    @PostMapping("/user/allow/add/{id}")
    @ApiResponse(responseCode = "200")
    @Operation(summary = "Добавляет в кассу доступных пользователю")
    public void putByAllowUser(@RequestBody UserDto user_allow,
                               @Parameter(description = "Идентификатор кассы, в которой добавляется разрешенный пользователь")
                               @PathVariable Long id) {

        cashService.putByAllowUser(id, user_allow);
    }

}
