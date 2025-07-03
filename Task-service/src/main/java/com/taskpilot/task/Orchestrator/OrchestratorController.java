package com.taskpilot.task.Orchestrator;

import com.taskpilot.task.dto.ProfileUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

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
