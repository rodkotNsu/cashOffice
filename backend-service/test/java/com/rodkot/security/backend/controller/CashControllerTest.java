package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.dto.CashDto;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.entity.Role;
import com.rodkot.security.backend.entity.User;
import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.mapper.CashMapper;
import com.rodkot.security.backend.services.CashService;
import com.rodkot.security.backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CashControllerTest {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final Role ROLE = new Role(null, "ROLE_ADMIN");
    private static final String EMAIL1 = "email@gmail.com";
    private static final String NAME_ORGANIZATION = "rodix";

    private static final String FULL_NAME = "fullName";
    @InjectMocks
    private CashController cashController;

    @Mock
    private CashService cashService;
    @Mock
    private CashMapper cashMapper;

    private CashDto cashDto;
    private User userDefault;
    private Organization organizationDefault;
    @BeforeEach
    private void initializeData() {
        userDefault = new User(1L, LOGIN, PASSWORD, List.of(ROLE), EMAIL1, FULL_NAME);
       organizationDefault = new Organization(null,NAME_ORGANIZATION,userDefault, OffsetDateTime.now());
        cashDto = new CashDto(null,organizationDefault,NAME_ORGANIZATION,1000L,null,null,OffsetDateTime.now());
    }
    @Test
    public void createCashSuccessful(){
        Cash cashToSave = new Cash(1L,organizationDefault,NAME_ORGANIZATION,1000L,null,null,OffsetDateTime.now());
        when(cashService.addCash(cashDto)).thenReturn(cashToSave);

        CashDto expectedCash = cashMapper.cashToCashDto(cashToSave);
        Response<CashDto> expectedResponse = Response.withData(expectedCash);

        Response<CashDto> actualResponse = cashController.create(cashDto);

        assertThat(actualResponse)
                .usingRecursiveComparison()
                .isEqualTo(expectedResponse);
    }
}