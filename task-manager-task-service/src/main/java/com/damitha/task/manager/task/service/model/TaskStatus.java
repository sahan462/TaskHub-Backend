package com.damitha.task.manager.task.service.model;

public enum TaskStatus {

    PENDING("PENDING"),
    ASSIGNED("ASSIGNED"),
    DONE("DONE");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
