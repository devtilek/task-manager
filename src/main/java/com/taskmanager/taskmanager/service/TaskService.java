package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskDTO getTaskById(UUID id);

    List<TaskDTO> getAllTasks();

    TaskDTO addTask(TaskDTO taskDTO);

    TaskDTO updateTask(UUID id, TaskDTO taskDTO);

    void deleteById(UUID id);
}
