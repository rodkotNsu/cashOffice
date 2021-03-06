package com.rodkot.security.backend.services;

import com.rodkot.security.backend.dto.CashDto;
import com.rodkot.security.backend.dto.OperationDto;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.entity.Cash;

import java.util.List;

public interface CashService {
    List<CashDto> getAll();

    CashDto getById(Long id);

    Cash addCash(CashDto cash);

    void removeById(Long id);

    List<CashDto> getByOrganization(Long id);

    List<CashDto> getByAllowUser(Long id);

    void putByAllowUser(Long idCash, UserDto userDto);
}
