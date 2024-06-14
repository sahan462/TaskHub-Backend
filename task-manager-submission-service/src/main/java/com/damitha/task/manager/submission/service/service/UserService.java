package com.damitha.task.manager.submission.service.service;

import com.damitha.task.manager.task.service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:5000")
public interface UserService {

    @GetMapping("/api/user/profile")
    public UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}
