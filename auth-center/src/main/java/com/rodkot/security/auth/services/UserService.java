package com.rodkot.security.auth.services;


import com.rodkot.security.auth.Role;
import com.rodkot.security.auth.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);

    User getUser(String username);

    void addRoleToUser(String username, String roleName);

    List<User> getUsers();
}
