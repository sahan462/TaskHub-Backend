package com.damitha.task.manager.service;

import com.damitha.task.manager.repository.UserRepository;
//import com.damitha.task.manager.config.JwtProvider;
import com.damitha.task.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserProfile(String email) {
//        String email = JwtProvider.getEmailFromJwtToken(jwt);
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
