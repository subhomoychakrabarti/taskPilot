package com.taskpilot.registration.service;

import com.taskpilot.registration.dto.SignInRequestEntity;
import com.taskpilot.registration.dto.SignUpRequestEntity;

public interface UserService<T> {
    T saveUser(SignUpRequestEntity signUpRequestEntity);

    void validateUser(SignUpRequestEntity signUpRequestEntity);
}
