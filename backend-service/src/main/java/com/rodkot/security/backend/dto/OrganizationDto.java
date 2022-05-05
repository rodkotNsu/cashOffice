package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
@Data
public class OrganizationDto {
    Long id;
    String name;
    User owner;
    OffsetDateTime createDataTime;
}
