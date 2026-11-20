package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.UserDTO;
import com.taskmanager.taskmanager.entity.User;
import com.taskmanager.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.taskmanager.mapper.UserMapper;
import com.taskmanager.taskmanager.repository.UserRepo;
import com.taskmanager.taskmanager.service.impl.UserServiceImpl;
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
class UserServiceImplTest {
    @Mock UserRepo users;
    @Mock UserMapper mapper;
    @InjectMocks UserServiceImpl service;

    @Test
    void missingUserThrowsNotFound() {
        UUID id = UUID.randomUUID();
        when(users.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getUserById(id));
    }

    @Test
    void createUserPersistsMappedEntity() {
        UserDTO request = new UserDTO();
        request.setName("Student");
        User entity = new User();
        UserDTO response = new UserDTO();
        when(mapper.toEntity(request)).thenReturn(entity);
        when(users.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(response);

        assertSame(response, service.addUser(request));
        verify(users).save(entity);
    }

    @Test
    void updateUserChangesName() {
        UUID id = UUID.randomUUID();
        UserDTO request = new UserDTO();
        request.setName("New name");
        User existing = new User();
        when(users.findById(id)).thenReturn(Optional.of(existing));
        when(users.save(existing)).thenReturn(existing);
        when(mapper.toDTO(existing)).thenReturn(new UserDTO());

        service.updateUser(id, request);

        assertEquals("New name", existing.getName());
        verify(users).save(existing);
    }

    @Test
    void deletingMissingUserDoesNotDelete() {
        UUID id = UUID.randomUUID();
        when(users.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.deleteById(id));
        verify(users, never()).delete(any());
    }
}
