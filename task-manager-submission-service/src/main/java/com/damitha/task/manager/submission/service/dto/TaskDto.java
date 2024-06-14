package com.damitha.task.manager.submission.service.dto;

import com.damitha.task.manager.task.service.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Integer taskId;

    private String title;
    private String description;
    private String image;
    private Integer assignedUserId;

    private List<String> tags = new ArrayList<>();

//    When an object containing a field annotated with @ElementCollection, such as a List<String> field, is saved to the
//    database in JPA (Java Persistence API), the elements of the list are stored in a separate table associated with the
//    owning entity.

    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
}

