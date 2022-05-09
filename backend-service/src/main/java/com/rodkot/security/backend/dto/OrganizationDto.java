package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
@Data
public class OrganizationDto {
    private Long id;
    private String name;
    private User owner;
    private OffsetDateTime createDataTime;
}
