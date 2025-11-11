package com.codealchemists.tasklist.dto;

import com.codealchemists.tasklist.model.TaskStatus;

import java.util.UUID;

public record TaskDto(
        UUID id,
        String shortDescription,
        String longDescription,
        TaskStatus status
) {}
