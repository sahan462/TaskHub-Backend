package com.damitha.task.manager.task.service.repository;

import com.damitha.task.manager.task.service.model.Task;
import com.damitha.task.manager.task.service.service.TaskServiceImplementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByAssignedUserId(Integer userId);
}
