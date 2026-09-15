package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepo extends JpaRepository<Task, UUID> {
}
