package com.taskpilot.registration.controller;

import com.taskpilot.registration.dto.SignInRequestEntity;
import com.taskpilot.registration.dto.SignUpRequestEntity;
import com.taskpilot.registration.dto.UpdateUserTaskRequest;
import com.taskpilot.registration.model.User;
import com.taskpilot.registration.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registrationService/handler")
public class UserController {
    UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    /**
     * Endpoint to register a new user.
     *
     * @param signUpRequestEntity the request body containing user details
     * @return ResponseEntity with the created User object
     */
    @PostMapping(path = "/signup")
    public ResponseEntity<User> registerUser(@RequestBody SignUpRequestEntity signUpRequestEntity) {
        userServiceImpl.validateUser(signUpRequestEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.saveUser(signUpRequestEntity));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> loginUser(@RequestBody SignInRequestEntity signInRequestEntity) {
        User user = userServiceImpl.login(signInRequestEntity);
        return ResponseEntity.ok().body(user);
    }


    @PatchMapping(path = "/updateTask")
    public ResponseEntity<User> updateUser( @RequestBody UpdateUserTaskRequest updateUserTaskRequest) {
        User user = userServiceImpl.updateExistingUserWithTaskId(updateUserTaskRequest);
        return ResponseEntity.ok().body(user);
    }




}
