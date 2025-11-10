package com.codealchemists.tasklist.dto;

import com.codealchemists.tasklist.model.TaskStatus;

import java.util.UUID;

public class TaskDto {
    public UUID id;
    public String shortDescription;
    public String longDescription;
    public TaskStatus status;
}
