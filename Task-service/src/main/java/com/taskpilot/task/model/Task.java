package com.taskpilot.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tasks")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "taskId", updatable = false, nullable = false)
    private UUID taskId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String taskDetails;

    private String sla;

    @Column(nullable = false)
    private String isCompleted;

    @ElementCollection
    private List<String> tags;
}
