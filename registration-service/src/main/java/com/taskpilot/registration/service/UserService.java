package com.taskpilot.registration.service;

import com.taskpilot.registration.dto.SignUpRequestEntity;
import com.taskpilot.registration.dto.UpdateUserTaskRequest;
import com.taskpilot.registration.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService<T> {
    T saveUser(SignUpRequestEntity signUpRequestEntity);

    void validateUser(SignUpRequestEntity signUpRequestEntity);

    User updateExistingUserWithTaskId(UpdateUserTaskRequest updateUserTaskRequest);

    List<UUID> getAllTasksIdForUser(String userName);
}
