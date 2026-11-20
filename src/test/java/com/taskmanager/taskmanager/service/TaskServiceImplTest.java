package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.entity.Task;
import com.taskmanager.taskmanager.entity.User;
import com.taskmanager.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.taskmanager.mapper.TaskMapper;
import com.taskmanager.taskmanager.repository.TaskRepo;
import com.taskmanager.taskmanager.repository.UserRepo;
import com.taskmanager.taskmanager.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock TaskMapper mapper;
    @Mock TaskRepo tasks;
    @Mock UserRepo users;
    @InjectMocks TaskServiceImpl service;

    @Test
    void missingTaskThrowsNotFound() {
        UUID id = UUID.randomUUID();
        when(tasks.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getTaskById(id));
    }

    @Test
    void missingOwnerPreventsTaskCreation() {
        UUID ownerId = UUID.randomUUID();
        TaskDTO request = new TaskDTO();
        request.setUserId(ownerId);
        when(users.findById(ownerId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.addTask(request));
        verifyNoInteractions(tasks);
    }

    @Test
    void createdTaskIsAssignedToExistingOwner() {
        UUID ownerId = UUID.randomUUID();
        TaskDTO request = new TaskDTO();
        request.setUserId(ownerId);
        User owner = new User();
        Task entity = new Task();
        TaskDTO response = new TaskDTO();
        when(users.findById(ownerId)).thenReturn(Optional.of(owner));
        when(mapper.toEntity(request)).thenReturn(entity);
        when(tasks.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(response);

        assertSame(response, service.addTask(request));
        assertSame(owner, entity.getUser());
        verify(tasks).save(entity);
    }

    @Test
    void updateChangesTaskFieldsAndOwner() {
        UUID id = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();
        TaskDTO request = new TaskDTO();
        request.setTitle("Updated");
        request.setDescription("Details");
        request.setUserId(ownerId);
        Task entity = new Task();
        User owner = new User();
        when(tasks.findById(id)).thenReturn(Optional.of(entity));
        when(users.findById(ownerId)).thenReturn(Optional.of(owner));
        when(tasks.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(new TaskDTO());

        service.updateTask(id, request);

        assertEquals("Updated", entity.getTitle());
        assertEquals("Details", entity.getDescription());
        assertSame(owner, entity.getUser());
    }

    @Test
    void missingTaskCannotBeDeleted() {
        UUID id = UUID.randomUUID();
        when(tasks.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.deleteById(id));
        verify(tasks, never()).delete(any());
    }
}
