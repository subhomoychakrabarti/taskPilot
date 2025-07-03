package com.taskpilot.task.controller;

import com.taskpilot.task.model.Task;
import com.taskpilot.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("taskService/handler/")
public class TaskController {

    TaskService service;

    TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTaskDetails());
    }

    @PostMapping("getAllTasksByIds")
    public ResponseEntity<List<Task>> getTasksByIds(@RequestBody List<UUID> taskIds) {
        return ResponseEntity.ok(service.getAllTaskByIds(taskIds));
    }

    @PostMapping("createTask")
    public ResponseEntity<Task> createTodo(@RequestBody Task task) {
        return ResponseEntity.ok(service.createTask(task));
    }

    @PutMapping("updateTask/{taskId}")
    public ResponseEntity<Task> updateTodo(@PathVariable UUID taskId, @RequestBody Task task) {
        return ResponseEntity.ok(service.updateTask(taskId, task));
    }

    @DeleteMapping("deleteTask/{taskId}")
    public ResponseEntity<String> deleteTodo(@PathVariable UUID taskId) {
        service.deleteTask(taskId);
        return ResponseEntity.ok("Todo deleted successfully");
    }
}
