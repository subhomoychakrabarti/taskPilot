package com.taskpilot.task.controller;

import com.taskpilot.task.dto.ProfileUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("taskService/orchestrate/handler/")
public class OrchestratorController {
    /**
     * Endpoint to update user task using a WebClient to communicate with the registration service.
     *
     * @param requestEntity the request body containing user details and task ID
     * @return ResponseEntity with a success message or error message
     */

    @PostMapping("/updateUserTask")
    public ResponseEntity<String> updateUserTask(
            @RequestBody ProfileUpdateRequest requestEntity) {

        try {
            WebClient.create("http://localhost:8080")
                    .patch()
                    .uri("/registrationService/handler/updateTask")
                    .bodyValue(requestEntity)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return ResponseEntity.ok("Task updated for user: " + requestEntity.getUserName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
