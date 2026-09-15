package com.taskmanager.taskmanager.service.impl;

import com.taskmanager.taskmanager.dto.UserDTO;
import com.taskmanager.taskmanager.entity.User;
import com.taskmanager.taskmanager.mapper.UserMapper;
import com.taskmanager.taskmanager.repository.UserRepo;
import com.taskmanager.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDTO getUserById(UUID id) {
        User user = userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public void addUser(UserDTO user) {
        User newUser = userMapper.toEntity(user);
        userRepo.save(newUser);
    }

    @Override
    public void deleteById(UUID id) {
        User user = userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        userRepo.delete(user);
    }
}
