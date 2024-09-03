package com.damitha.task.manager.task.service.controller;

import com.damitha.task.manager.task.service.dto.UserDto;
import com.damitha.task.manager.task.service.model.Task;
import com.damitha.task.manager.task.service.model.TaskStatus;
import com.damitha.task.manager.task.service.service.TaskService;
import com.damitha.task.manager.task.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {
        System.out.println("create task controller");
        log.info("create task controller");
//        UserDto userProfile = userService.getUserProfile(jwt);
        Task createdTask = taskService.createTask(task, "roleAdmin");

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable String taskId, @RequestHeader("Authorization") String jwt) throws Exception {

        Optional<Task> createdTask = taskService.getTaskById(Integer.valueOf(taskId));

        return new ResponseEntity<>(createdTask, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false)TaskStatus status, @RequestHeader("Authorization") String jwt) throws Exception {

        List<Task> tasks = taskService.getAllTasks(status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userProfile = userService.getUserProfile(jwt);
        Task updatedTask = taskService.updateTask(task.getTaskId(), task, Integer.valueOf(userProfile.getId()));

        return new ResponseEntity<>(updatedTask, HttpStatus.CREATED);

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable String taskId) throws Exception {

        taskService.deleteTask(Integer.valueOf(taskId));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/{taskId}/user/{userId}/assigned")
    public ResponseEntity<Task> assignedTaskToUser(
            @PathVariable Integer taskId,
            @PathVariable Integer userId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        Task assignedTask = taskService.assignedToUser(userId, taskId);

        return new ResponseEntity<>(assignedTask, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUserTask(
            @RequestParam(required = false)TaskStatus status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        UserDto userProfile = userService.getUserProfile(jwt);
        List<Task> assignedTasks = taskService.assignedUserTasks(Integer.valueOf(userProfile.getId()), status);

        return new ResponseEntity<>(assignedTasks, HttpStatus.OK);

    }

    @PutMapping ("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable String taskId, @RequestHeader("Authorization") String jwt) throws Exception {

        Task completedTask = taskService.completeTask(taskId);

        return new ResponseEntity<>(completedTask, HttpStatus.OK);

    }

}
