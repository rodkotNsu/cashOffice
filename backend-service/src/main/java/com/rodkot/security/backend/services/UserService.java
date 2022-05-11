package com.rodkot.security.backend.services;



import com.rodkot.security.backend.dto.OrganizationDto;
import com.rodkot.security.backend.dto.RoleDto;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.entity.Role;
import com.rodkot.security.backend.entity.User;

import java.util.List;

public interface UserService {

    User getUser(String username);

    void addRoleToUser(String username, String roleName);

    List<User> getUsers();

    List<UserDto> getAll();

    User updateUser(Long idUser, UserDto userDto);

    void deleteUser(Long idUser);

    User getUserById(Long idUser);

    User saveUser(UserDto userDTO);
}
