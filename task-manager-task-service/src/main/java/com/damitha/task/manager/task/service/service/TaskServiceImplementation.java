package com.damitha.task.manager.task.service.service;

import com.damitha.task.manager.task.service.model.Task;
import com.damitha.task.manager.task.service.model.TaskStatus;
import com.damitha.task.manager.task.service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplementation implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, String requesterRole) throws Exception {
        if(!requesterRole.equals("roleAdmin")){
            throw new Exception("Only admins are allowed to create tasks.");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(Integer id) throws Exception {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllTasks(TaskStatus status) throws Exception {
        List<Task> allTasks = taskRepository.findAll();

        List<Task> filteredTasks = allTasks.stream().filter(
                task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());

        return filteredTasks;
    }

    @Override
    public Task updateTask(Integer taskId, Task updatedTask, Integer userId) throws Exception {

        Optional<Task> existingTaskOptional  = getTaskById(taskId);

        if(existingTaskOptional.isPresent()){

            Task existingTask = existingTaskOptional.get();

            if(updatedTask.getTitle()!=null){
                existingTask.setTitle(updatedTask.getTitle());
            }

            if(updatedTask.getImage()!=null){
                existingTask.setImage(updatedTask.getImage());
            }

            if(updatedTask.getDescription()!=null){
                existingTask.setDescription(updatedTask.getDescription());
            }

            if(updatedTask.getStatus()!=null){
                existingTask.setStatus(updatedTask.getStatus());
            }

            if(updatedTask.getDeadline()!=null){
                existingTask.setDeadline(updatedTask.getDeadline());
            }

            return taskRepository.save(existingTask);


        }else{

            throw new Exception("Task with ID " + taskId + " not found");

        }

    }

    @Override
    public void deleteTask(Integer taskId) throws Exception {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task assignedToUser(Integer userId, Integer taskId) throws Exception {

        Optional<Task> existingTaskOptional  = getTaskById(taskId);

        if(existingTaskOptional.isPresent()){

            Task existingTask = existingTaskOptional.get();
            existingTask.setAssignedUserId(userId);

            return existingTask;

        }else{

            throw new Exception("Task with ID " + taskId + " not found");

        }

    }

    @Override
    public List<Task> assignedUserTasks(Integer userId, TaskStatus status) throws Exception {
        // Fetch all tasks assigned to the user
        List<Task> userTasks = taskRepository.findByAssignedUserId(userId);

        // If a status is provided, filter the tasks by this status
        if (status != null) {
            userTasks = userTasks.stream()
                    .filter(task -> task.getStatus() == status)
                    .collect(Collectors.toList());
        }

        return userTasks;
    }


    @Override
    public Task completeTask(Integer taskId) throws Exception {
        // Find the task by its ID
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);

        if (existingTaskOptional.isPresent()) {
            // Retrieve the task from the Optional
            Task existingTask = existingTaskOptional.get();

            // Mark the task as complete
            existingTask.setStatus(TaskStatus.DONE);

            // Save the updated task back to the repository
            return taskRepository.save(existingTask);
        } else {
            // Handle the case where the task was not found
            throw new Exception("Task with ID " + taskId + " not found");
        }
    }

}
