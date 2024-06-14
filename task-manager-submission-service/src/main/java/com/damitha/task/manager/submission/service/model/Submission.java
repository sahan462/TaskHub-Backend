package com.damitha.task.manager.submission.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer submissionId;

    private Integer taskId;
    private String gitHubLink;
    private Integer userId;
    private String status="PENDING";
    private LocalDateTime submittedDate;

}
