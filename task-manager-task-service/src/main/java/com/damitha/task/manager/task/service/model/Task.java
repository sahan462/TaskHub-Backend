package com.damitha.task.manager.task.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;

    private String title;
    private String description;
    private String image;
    private Integer assignedUserId;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;




}
