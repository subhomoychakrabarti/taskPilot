package com.taskpilot.registration.service;

import com.taskpilot.registration.dto.SignUpRequestEntity;
import com.taskpilot.registration.dto.UpdateUserTaskRequest;
import com.taskpilot.registration.model.User;

public interface UserService<T> {
    T saveUser(SignUpRequestEntity signUpRequestEntity);

    void validateUser(SignUpRequestEntity signUpRequestEntity);

    User updateExistingUserWithTaskId(UpdateUserTaskRequest updateUserTaskRequest);
}
