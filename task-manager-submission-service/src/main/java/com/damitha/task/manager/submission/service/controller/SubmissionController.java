package com.damitha.task.manager.submission.service.controller;

import com.damitha.task.manager.submission.service.model.Submission;
import com.damitha.task.manager.submission.service.service.SubmissionService;
import com.damitha.task.manager.submission.service.service.UserService;
import com.damitha.task.manager.task.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Submission> submitTask(
        @RequestParam Integer taskId,
        @RequestParam String gitHubLink,
        @RequestHeader("Authorization") String jwt
    ) throws Exception{

        UserDto user = userService.getUserProfile(jwt);
        Submission submission  = submissionService.submitTask(taskId, gitHubLink, user.getUserId(), jwt);

        return new ResponseEntity<>(submission, HttpStatus.OK);

    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<Optional<Submission>> getTaskSubmissionById(
            @PathVariable Integer submissionId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{

        Optional<Submission> submission = submissionService.getTaskSubmissionById(submissionId);

        return new ResponseEntity<>(submission, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllTaskSubmissions(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{

        List<Submission> submissions = submissionService.getAllTaskSubmissions();

        return new ResponseEntity<>(submissions, HttpStatus.OK);

    }

    @GetMapping("/task")
    public ResponseEntity<List<Submission>> getTaskSubmissionByTaskId(
            @RequestParam String taskId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{

        List<Submission> submission = submissionService.getTaskSubmissionsByTaskId(Integer.valueOf(taskId));

        return new ResponseEntity<>(submission, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Submission> acceptDeclineSubmission(
            @RequestParam String submissionId,
            @RequestParam String status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{

//        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptDeclineSubmission(Integer.valueOf(submissionId), status, jwt);

        return new ResponseEntity<>(submission, HttpStatus.OK);

    }








}
