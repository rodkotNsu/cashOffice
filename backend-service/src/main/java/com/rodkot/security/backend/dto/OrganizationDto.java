package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.User;

import javax.persistence.*;
import java.time.OffsetDateTime;

public class OrganizationDto {
    Long id;
    String name;
    User owner;
    OffsetDateTime createDataTime;
}
