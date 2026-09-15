package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable UUID id){
        return taskService.getTaskById(id);
    }

    @PostMapping
    public void addUser(@RequestBody TaskDTO taskDTO){
        taskService.addTask(taskDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        taskService.deleteById(id);
    }
}
