package com.damitha.task.manager.submission.service.service;

import com.damitha.task.manager.submission.service.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(name = "TASK-SERVICE", url = "http://localhost:5001")
public interface TaskService {

    @GetMapping("/api/tasks/{taskId}")
    public ResponseEntity<Optional<TaskDto>> getTaskById(@PathVariable String taskId, @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/api/tasks/{taskId}/complete")
    public ResponseEntity<TaskDto> completeTask(@PathVariable String taskId, @RequestHeader("Authorization") String jwt) throws Exception;

}
