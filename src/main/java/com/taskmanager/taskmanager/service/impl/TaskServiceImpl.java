package com.taskmanager.taskmanager.service.impl;

import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.entity.Task;
import com.taskmanager.taskmanager.entity.User;
import com.taskmanager.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.taskmanager.mapper.TaskMapper;
import com.taskmanager.taskmanager.repository.TaskRepo;
import com.taskmanager.taskmanager.repository.UserRepo;
import com.taskmanager.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public TaskDTO getTaskById(UUID id) {
        return taskMapper.toDTO(findTask(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks() {
        return taskRepo.findAll().stream()
                .map(taskMapper::toDTO)
                .toList();
    }

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        User user = findUser(taskDTO.getUserId());
        Task task = taskMapper.toEntity(taskDTO);
        task.setUser(user);
        return taskMapper.toDTO(taskRepo.save(task));
    }

    @Override
    public TaskDTO updateTask(UUID id, TaskDTO taskDTO) {
        Task task = findTask(id);
        User user = findUser(taskDTO.getUserId());

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setUser(user);

        return taskMapper.toDTO(taskRepo.save(task));
    }

    @Override
    public void deleteById(UUID id) {
        taskRepo.delete(findTask(id));
    }

    private Task findTask(UUID id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
    }

    private User findUser(UUID id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }
}
