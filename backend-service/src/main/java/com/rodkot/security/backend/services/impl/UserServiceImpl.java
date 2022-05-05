package com.rodkot.security.backend.services.impl;


import com.rodkot.security.backend.dto.RoleDto;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.entity.Role;
import com.rodkot.security.backend.entity.User;
import com.rodkot.security.backend.mapper.UserMapper;
import com.rodkot.security.backend.repository.RoleRepo;
import com.rodkot.security.backend.repository.UserRepo;
import com.rodkot.security.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    private final UserMapper userMapper;


    @Override
    public User saveUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user:users) {
            userDtos.add(userMapper.userToUserDto(user));
        }
        return userDtos;
    }

    @Override
    public void addUser(UserDto user) {
        userRepo.save(userMapper.userDtoToUser(user));
    }

    @Override
    public void addRole(RoleDto role) {
roleRepo.save(new Role(0L,"ROLE_ADMIN"));
    }


}
