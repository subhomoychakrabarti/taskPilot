package com.taskpilot.registration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("registrationService/orchestrate/handler/")
public class OrchestratorController {

    /**
     * Endpoint to get all tasks for a user by their username.
     * This method orchestrates calls to the registration service and task service.
     *
     * @param username the username of the user
     * @return ResponseEntity containing the list of TaskBody objects or an error message
     */

    @GetMapping("getTasksForUserByUsername/{username}")
    public ResponseEntity<?> getTasksForUserByUsername(@PathVariable String username) {
        try {
            // Step 1: Call existing controller/service to get the list of Task IDs
            List<UUID> taskIds = WebClient.create("http://localhost:8080")
                    .get()
                    .uri("/registrationService/handler/getAllTasksIDForUser/" + username)
                    .retrieve()
                    .bodyToFlux(UUID.class)
                    .collectList()
                    .block();

            if (taskIds == null || taskIds.isEmpty()) {
                return ResponseEntity.status(404).body("No tasks found for username: " + username);
            }

            // Step 2: Use the taskIds to call the Task Service and get task details
            List<TaskBody> taskDetails = WebClient.create("http://localhost:8081")
                    .post()
                    .uri("taskService/handler/getAllTasksByIds")
                    .bodyValue(taskIds)
                    .retrieve()
                    .bodyToFlux(TaskBody.class)
                    .collectList()
                    .block();

            return ResponseEntity.ok(taskDetails);

        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

}
