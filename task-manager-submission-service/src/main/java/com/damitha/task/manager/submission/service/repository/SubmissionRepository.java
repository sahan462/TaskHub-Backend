package com.damitha.task.manager.submission.service.repository;

import com.damitha.task.manager.submission.service.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

    List<Submission> findByTaskId(Integer taskId);
}
