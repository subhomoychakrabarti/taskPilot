package com.taskpilot.model;

import java.util.List;

public class TaskBuilder {
    private String title;
    private String taskDetails;
    private String sla;
    private String isCompleted;
    private List<String> tags;

    public TaskBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskBuilder setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
        return this;
    }

    public TaskBuilder setSla(String sla) {
        this.sla = sla;
        return this;
    }

    public TaskBuilder setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
        return this;
    }

    public TaskBuilder setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Task build() {
        Task task = new Task();
        task.setTitle(title);
        task.setTaskDetails(taskDetails);
        task.setSla(sla);
        task.setIsCompleted(isCompleted);
        task.setTags(tags);
        return task;
    }
}
