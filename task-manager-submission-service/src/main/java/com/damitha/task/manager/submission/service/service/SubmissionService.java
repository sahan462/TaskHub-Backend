package com.damitha.task.manager.submission.service.service;

import com.damitha.task.manager.submission.service.model.Submission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SubmissionService {

    public Submission submitTask(Integer taskId, String gitHubLink, Integer userId, String jwt) throws Exception;

    public Optional<Submission> getTaskSubmissionById(Integer submissionId) throws Exception;
    public List<Submission> getAllTaskSubmissions() throws Exception;
    public List<Submission> getTaskSubmissionsByTaskId(Integer taskId) throws Exception;
    public Submission acceptDeclineSubmission(Integer submissionId, String status, String jwt) throws Exception;


}
