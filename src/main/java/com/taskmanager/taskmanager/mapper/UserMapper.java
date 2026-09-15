package com.taskmanager.taskmanager.mapper;

import com.taskmanager.taskmanager.dto.UserDTO;
import com.taskmanager.taskmanager.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
