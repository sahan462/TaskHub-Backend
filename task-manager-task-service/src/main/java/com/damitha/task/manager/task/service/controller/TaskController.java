package com.damitha.task.manager.task.service.controller;

import com.damitha.task.manager.task.service.dto.UserDto;
import com.damitha.task.manager.task.service.model.Task;
import com.damitha.task.manager.task.service.model.TaskStatus;
import com.damitha.task.manager.task.service.service.TaskService;
import com.damitha.task.manager.task.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {
        System.out.println("called");
        UserDto userProfile = userService.getUserProfile(jwt);
        Task createdTask = taskService.createTask(task, userProfile.getRole());

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable String taskId) throws Exception {

        Optional<Task> createdTask = taskService.getTaskById(Integer.valueOf(taskId));

        return new ResponseEntity<>(createdTask, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false)TaskStatus status) throws Exception {

        List<Task> tasks = taskService.getAllTasks(status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userProfile = userService.getUserProfile(jwt);
        Task updatedTask = taskService.updateTask(task.getTaskId(), task, userProfile.getUserId());

        return new ResponseEntity<>(updatedTask, HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<Object> deleteTask(@RequestBody Task task) throws Exception {

        taskService.deleteTask(task.getTaskId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/{taskId}/user/{userId}/assigned")
    public ResponseEntity<Task> assignedTaskToUser(
            @PathVariable Integer taskId,
            @PathVariable Integer userId
    ) throws Exception {

        Task assignedTask = taskService.assignedToUser(userId, taskId);

        return new ResponseEntity<>(assignedTask, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUserTask(
            @RequestHeader("Authorization") String jwt,
            @RequestParam(required = false)TaskStatus status) throws Exception {

        UserDto userProfile = userService.getUserProfile(jwt);
        List<Task> assignedTasks = taskService.assignedUserTasks(userProfile.getUserId(), status);

        return new ResponseEntity<>(assignedTasks, HttpStatus.OK);

    }

    @PostMapping("/complete")
    public ResponseEntity<Task> completeTask(@RequestBody Task task) throws Exception {

        Task completedTask = taskService.completeTask(task.getTaskId());

        return new ResponseEntity<>(completedTask, HttpStatus.OK);

    }

}
