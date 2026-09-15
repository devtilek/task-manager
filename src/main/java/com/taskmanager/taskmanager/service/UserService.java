package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDTO getUserById(UUID id);

    List<UserDTO> getAllUsers();

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UUID id, UserDTO userDTO);

    void deleteById(UUID id);
}
