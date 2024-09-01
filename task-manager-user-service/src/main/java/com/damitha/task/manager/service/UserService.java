package com.damitha.task.manager.service;

import com.damitha.task.manager.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User saveUser(User user);

    public User getUserProfile(String email);
    public List<User> getAllUsers();

}
