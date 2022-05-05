package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.Operation;
import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Data
public class CashDto {
    Long id;
    Organization organization;
    Long money;
    Collection<Operation> operations = new ArrayList<>();
    Collection<User> allowUsers = new ArrayList<>();
}
