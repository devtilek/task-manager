package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.UserDTO;
import com.taskmanager.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }

    @PostMapping("")
    public void addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id){
        userService.deleteById(id);
    }
}
