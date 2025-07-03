package com.taskpilot.registration.model;

import java.sql.Time;
import java.time.Instant;
import java.util.UUID;

public class UserBuilder {
    private String username;
    private String email;


    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public User build() {
        return new User(username, email);
    }
}
