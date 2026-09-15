package com.taskmanager.taskmanager.mapper;

import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "user.id", target = "userId")
    TaskDTO toDTO(Task task);

    @Mapping(target = "user", ignore = true)
    Task toEntity(TaskDTO taskDTO);
}
