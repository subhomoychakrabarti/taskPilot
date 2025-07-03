package com.taskpilot.task.service;

import com.taskpilot.task.exception.TaskNotFoundException;
import com.taskpilot.task.model.Task;
import com.taskpilot.task.model.TaskBuilder;
import com.taskpilot.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    TaskRepository repository;

    TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTaskDetails() {
        return repository.findAll();
    }

    public Task createTask(Task task) {
        Task newTask = new TaskBuilder()
                .setTitle(task.getTitle())
                .setTaskDetails(task.getTaskDetails())
                .setSla(task.getSla())
                .setIsCompleted(task.getIsCompleted())
                .setTags(task.getTags())
                .build();
        return repository.save(newTask);
    }

    public Task updateTask(UUID taskId, Task task) {
        Task existingTask = repository.findByTaskId(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Todo not found with id: " + taskId));
        existingTask.setTitle(task.getTitle());
        existingTask.setTaskDetails(task.getTaskDetails());
        existingTask.setTags(task.getTags());
        existingTask.setIsCompleted(task.getIsCompleted());
        existingTask.setSla(task.getSla());
        return repository.save(existingTask);
    }

    public void deleteTask(UUID id) {
        if (!repository.existsByTaskId(id)) {
            throw new TaskNotFoundException("Todo not found with id: " + id);
        }
        repository.deleteByTaskId(id);
    }

    public Task getTaskForId(UUID taskId) {
        return repository.findByTaskId(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Todo not found with id: " + taskId));
    }
}
