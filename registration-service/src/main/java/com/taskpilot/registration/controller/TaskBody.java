package com.taskpilot.registration.controller;

import lombok.Data;

import java.util.List;

@Data
public class TaskBody {

    private String title;
    private String taskDetails;
    private String sla;
    private String isCompleted;
    private List<String> tags;
}
