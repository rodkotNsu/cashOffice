package com.rodkot.security.backend.mapper;

import com.rodkot.security.backend.dto.OrganizationDto;
import com.rodkot.security.backend.dto.UserDto;
import com.rodkot.security.backend.entity.Organization;
import com.rodkot.security.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
