package com.rodkot.security.backend.dto;

import com.rodkot.security.backend.entity.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Collection<Role> roles= new ArrayList<>();
    private String email;
    private String fullName;
}
