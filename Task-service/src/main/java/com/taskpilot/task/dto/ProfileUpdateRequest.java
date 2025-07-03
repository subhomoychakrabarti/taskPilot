package com.taskpilot.task.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProfileUpdateRequest {
    String userName;
    UUID taskId;
}
