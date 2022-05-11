package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.Cash;
import com.rodkot.security.backend.entity.Operation;
import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
@Data
@AllArgsConstructor
public class CashDto {

    private Long id;

    private Organization organization;

    private String name;

    private Long money;

    private Collection<Operation> operations = new ArrayList<>();

    private Collection<User> allowUsers = new ArrayList<>();

    private OffsetDateTime dateTimeCreated;
}
