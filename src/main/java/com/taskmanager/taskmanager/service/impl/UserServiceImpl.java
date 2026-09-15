package com.taskmanager.taskmanager.service.impl;

import com.taskmanager.taskmanager.dto.UserDTO;
import com.taskmanager.taskmanager.entity.User;
import com.taskmanager.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.taskmanager.mapper.UserMapper;
import com.taskmanager.taskmanager.repository.UserRepo;
import com.taskmanager.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(UUID id) {
        return userMapper.toDTO(findUser(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepo.save(user));
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        User user = findUser(id);
        user.setName(userDTO.getName());
        return userMapper.toDTO(userRepo.save(user));
    }

    @Override
    public void deleteById(UUID id) {
        userRepo.delete(findUser(id));
    }

    private User findUser(UUID id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }
}
