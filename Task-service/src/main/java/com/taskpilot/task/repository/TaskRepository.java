package com.taskpilot.task.repository;

import com.taskpilot.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findByTaskId(UUID taskId);

    boolean existsByTaskId(UUID taskID);

    void deleteByTaskId(UUID taskId);
}
