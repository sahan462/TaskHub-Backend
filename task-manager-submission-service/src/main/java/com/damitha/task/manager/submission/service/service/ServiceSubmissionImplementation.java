package com.damitha.task.manager.submission.service.service;

import com.damitha.task.manager.submission.service.dto.TaskDto;
import com.damitha.task.manager.submission.service.model.Submission;
import com.damitha.task.manager.submission.service.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceSubmissionImplementation implements SubmissionService{

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private TaskService taskService;

    @Override
    public Submission submitTask(Integer taskId, String gitHubLink, Integer userId, String jwt) throws Exception {

        ResponseEntity<Optional<TaskDto>> task = taskService.getTaskById(String.valueOf(taskId), jwt);

        if(task != null){

            Submission newSubmission = new Submission();

            newSubmission.setTaskId(taskId);
            newSubmission.setGitHubLink(gitHubLink);
            newSubmission.setUserId(userId);
            newSubmission.setSubmittedDate(LocalDateTime.now());

            return submissionRepository.save(newSubmission);

        }else {

            throw new Exception("Task not found with id: " + taskId);

        }

    }

    @Override
    public Optional<Submission> getTaskSubmissionById(Integer submissionId) throws Exception {

        return submissionRepository.findById(submissionId);

    }

    @Override
    public List<Submission> getAllTaskSubmissions() throws Exception {

        return submissionRepository.findAll();

    }

    @Override
    public List<Submission> getTaskSubmissionsByTaskId(Integer taskId) throws Exception {

        return submissionRepository.findByTaskId(taskId);

    }

    @Override
    public Submission acceptDeclineSubmission(Integer submissionId, String status, String jwt) throws Exception {

        Optional<Submission> optionalSubmission = getTaskSubmissionById(submissionId);

        if(optionalSubmission.isPresent()){

            Submission submission = optionalSubmission.get();
            submission.setStatus(status);

            if(status.equals("ACCEPT")){
               taskService.completeTask(String.valueOf(submission.getTaskId()), jwt);
            }

            return submission;

        }else{

            throw new Exception("Submission not found with the given id" + submissionId);

        }

    }
}
