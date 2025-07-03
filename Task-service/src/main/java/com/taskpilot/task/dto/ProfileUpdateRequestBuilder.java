package com.taskpilot.task.dto;

import java.util.UUID;

public class ProfileUpdateRequestBuilder {
    private String userName;
    private UUID taskId;

    public ProfileUpdateRequestBuilder setUsername(String userName) {
        this.userName = userName;
        return this;
    }

    public ProfileUpdateRequestBuilder setTaskId(UUID taskId) {
        this.taskId = taskId;
        return this;
    }

    public ProfileUpdateRequest build() {
        ProfileUpdateRequest request = new ProfileUpdateRequest();
        request.setUserName(this.userName);
        request.setTaskId(this.taskId);
        return request;
    }
}
