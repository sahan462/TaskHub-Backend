package com.damitha.task.manager.task.service.service;

import com.damitha.task.manager.task.service.model.Task;
import com.damitha.task.manager.task.service.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {

    public Task createTask(Task task, String requesterRole) throws Exception;
    public Optional<Task> getTaskById(Integer id) throws Exception;
    public List<Task> getAllTasks(TaskStatus status) throws Exception;
    public Task updateTask(Integer taskId, Task updatedTask, Integer userId) throws Exception;
    public void deleteTask(Integer taskId) throws Exception;
    public Task assignedToUser(Integer userId, Integer taskId) throws Exception;
    public List<Task> assignedUserTasks(Integer userId, TaskStatus status) throws Exception;
    public Task completeTask(String taskId) throws Exception;

}
