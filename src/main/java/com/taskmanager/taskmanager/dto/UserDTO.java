package com.taskmanager.taskmanager.dto;

import com.taskmanager.taskmanager.entity.Task;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    String name;
    List<Task> tasks;
}
