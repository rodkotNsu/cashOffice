package com.rodkot.security.backend.services.impl;


import com.rodkot.security.backend.dto.RoleDto;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.entity.Role;
import com.rodkot.security.backend.entity.User;
import com.rodkot.security.backend.exception.BadRequestException;
import com.rodkot.security.backend.exception.NotFoundException;
import com.rodkot.security.backend.mapper.UserMapper;
import com.rodkot.security.backend.repository.RoleRepo;
import com.rodkot.security.backend.repository.UserRepo;
import com.rodkot.security.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.dao.DataIntegrityViolationException;
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
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    private final UserMapper userMapper;


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
        for (User user : users) {
            userDtos.add(userMapper.userToUserDto(user));
        }
        return userDtos;
    }


    @Override
    public User updateUser(Long idUser, UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setId(idUser);
        return saveUser(userMapper.userToUserDto(user));
    }

    @Override
    public void deleteUser(Long idUser) {
        User user = getUserById(idUser);
        userRepo.delete(user);
    }

    @Override
    public User getUserById(Long idUser) {
        return userRepo.findById(idUser).orElseThrow(
                () -> new NotFoundException("Not found user with id " + idUser));
    }

    @Override
    public User saveUser(UserDto userDTO) {
        User userToSave = userMapper.userDtoToUser(userDTO);
        try {
            return userRepo.save(userToSave);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Уже есть пользователь с таким именем");
        }
        catch (Exception e){
            return null;
        }

    }
}
