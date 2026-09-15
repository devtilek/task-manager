package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TaskService {
    TaskDTO getTaskById(UUID id);
    void addTask(TaskDTO taskDTO);
    void deleteById(UUID id);
}
