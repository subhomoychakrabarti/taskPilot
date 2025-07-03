package com.taskpilot.registration.service;

import com.taskpilot.registration.dto.SignInRequestEntity;
import com.taskpilot.registration.dto.SignUpRequestEntity;
import com.taskpilot.registration.exception.LoginValidationFailedException;
import com.taskpilot.registration.model.User;
import com.taskpilot.registration.model.UserBuilder;
import com.taskpilot.registration.repotitory.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService<User> {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User saveUser(SignUpRequestEntity signUpRequestEntity) {
        User user = new UserBuilder()
                .setUsername(signUpRequestEntity.getUserName())
                .setEmail(signUpRequestEntity.getEmail())
                .build();

        return userRepository.save(user);
    }

    @Override
    public void validateUser(SignUpRequestEntity signUpRequestEntity) {
        if (userRepository.existsByUsername(signUpRequestEntity.getUserName())) {
            throw new LoginValidationFailedException("Username Already Taken");
        }
        // Check if email already exists
        if (userRepository.existsByEmail((signUpRequestEntity.getEmail()))) {
            throw new LoginValidationFailedException("Email Already Taken");
        }
    }

    public User login(SignInRequestEntity signInRequestEntity) {
        Optional<User> user = userRepository.findByUsername(signInRequestEntity.getUserName());
        if (user.isPresent()) {
            return user.get();
        }
        throw new LoginValidationFailedException("Invalid Username or Password");
    }
}
