package com.taskpilot.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    public static final Logger log = LoggerFactory.getLogger(GatewayController.class);

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("service", "TaskPilot Gateway");
        response.put("version", "1.0.0");
        
        log.info("Health check requested");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "TaskPilot Gateway");
        info.put("description", "API Gateway for TaskPilot microservices");
        info.put("routes", Map.of(
            "auth", "/api/auth/** -> Registration Service (port 8081)",
            "tasks", "/api/tasks/** -> Task Service (port 8082)"
        ));
        info.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(info);
    }

    @GetMapping("/test-task-routing")
    public ResponseEntity<Map<String, Object>> testTaskRouting() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Gateway is working");
        response.put("taskServiceUrl", "http://localhost:8082");
        response.put("gatewayTaskUrl", "http://localhost:8080/api/tasks");
        response.put("example", "Try: GET /api/tasks/taskService/orchestrate/handler/getTasksForUserByUsername/Subhomoy");
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }
} 