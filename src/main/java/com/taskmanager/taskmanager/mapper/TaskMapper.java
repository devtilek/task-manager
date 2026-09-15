package com.taskmanager.taskmanager.mapper;

import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.entity.Task;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDTO(Task task);
    Task toEntity(TaskDTO taskDTO);

}
