package com.rodkot.security.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RoleDto {
    private Long id;

    @NotEmpty
    @Size(max = 30  , message = "название роли превышает лимит")
    private String name;
}
