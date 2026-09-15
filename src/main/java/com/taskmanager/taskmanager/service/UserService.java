package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.UserDTO;
import com.taskmanager.taskmanager.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface UserService {
    UserDTO getUserById(UUID id);
    void addUser(UserDTO user);
    void deleteById(UUID id);
}
