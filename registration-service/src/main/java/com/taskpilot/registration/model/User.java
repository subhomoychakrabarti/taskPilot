package com.taskpilot.registration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true)
    private List<UUID> taskIds;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private Instant lastActive;


    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.lastActive = Instant.now();
        this.isActive = true; // Default to active when a user is created
    }
}
