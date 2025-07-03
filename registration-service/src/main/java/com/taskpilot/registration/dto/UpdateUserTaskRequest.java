package com.taskpilot.registration.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserTaskRequest {
    String userName;
    UUID taskId;
}
