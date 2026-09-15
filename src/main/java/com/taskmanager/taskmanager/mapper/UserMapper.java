package com.taskmanager.taskmanager.mapper;

import com.taskmanager.taskmanager.dto.UserDTO;

import com.taskmanager.taskmanager.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> USER_DTO_LIST(List<User> users);
    List<User> USERS(List<UserDTO> userDTOS);
}
