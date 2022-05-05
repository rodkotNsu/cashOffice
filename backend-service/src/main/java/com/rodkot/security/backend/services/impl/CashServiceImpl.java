package com.rodkot.security.backend.services.impl;

import com.rodkot.security.backend.dto.CashDto;
import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.exception.BadRequestException;
import com.rodkot.security.backend.mapper.CashMapper;
import com.rodkot.security.backend.repository.CashRepo;
import com.rodkot.security.backend.services.CashService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CashServiceImpl implements CashService {
    private final CashRepo cashRepo;
    private final CashMapper cashMapper;


    @Override
    public List<CashDto> getAll() {
        List<CashDto> cashDtoList = new ArrayList<>();
        for (Cash cash : cashRepo.findAll()) {
            cashDtoList.add(cashMapper.cashToCashDto(cash));
        }
        return cashDtoList;
    }

    @Override
    public CashDto getById(Long id) {
        Cash cash = cashRepo.findById(id).orElseThrow(() -> new BadRequestException("Касса не найдена"));
        return cashMapper.cashToCashDto(cash);
    }

    @Override
    public void addCash(CashDto cashDto) {
        Cash cash = cashMapper.cashDtoToCash(cashDto);
        cashRepo.save(cash);
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public List<CashDto> getByOrganization(Long id) {
        return null;
    }

    @Override
    public List<CashDto> getByAllowUser(Long id) {
        return null;
    }
}
