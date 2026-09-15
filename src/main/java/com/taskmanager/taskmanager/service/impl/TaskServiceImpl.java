package com.taskmanager.taskmanager.service.impl;

import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.entity.Task;
import com.taskmanager.taskmanager.mapper.TaskMapper;
import com.taskmanager.taskmanager.repository.TaskRepo;
import com.taskmanager.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;
    private final TaskRepo taskRepo;


    @Override
    public TaskDTO getTaskById(UUID id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found"));
        return taskMapper.toDTO(task);
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        taskRepo.save(task);
    }

    @Override
    public void deleteById(UUID id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepo.delete(task);
    }
}
