package com.rodkot.security.backend.mapper;

import com.rodkot.security.backend.dto.CashDto;
import com.rodkot.security.backend.entity.Cash;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashMapper {
    Cash cashDtoToCash(CashDto cashDto);

    CashDto cashToCashDto(Cash cash);
}
