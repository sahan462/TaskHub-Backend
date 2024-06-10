package com.damitha.task.manager.controller;

import com.damitha.task.manager.repository.UserRepository;
import com.damitha.task.manager.requests.LoginRequest;
import com.damitha.task.manager.response.AuthResponse;
import com.damitha.task.manager.config.JwtProvider;
import com.damitha.task.manager.model.User;
import com.damitha.task.manager.service.CustomerUserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserServiceImplementation customerUserDetails;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

        //Spring Boot, through the use of the @RequestBody annotation, will automatically map the JSON fields to the fields of the User object.

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String mobile = user.getMobile();
        String role = user.getRole();

        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist!=null){
            throw new Exception("Email is Already used with Another Accounf");
        }

        //create new user
        User createdUser = new User();

        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setRole(role);
        createdUser.setMobile(mobile);

        userRepository.save(createdUser);

        // Create an Authentication object using the email and password provided
        // UsernamePasswordAuthenticationToken is a built-in implementation of the Authentication interface
        // It is typically used to represent the username and password-based authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        // Set the created Authentication object in the SecurityContext
        // The SecurityContext is a container for storing the security information of the current request thread
        // SecurityContextHolder is a helper class for accessing the security context
        // Setting the authentication in the context indicates that the current user is authenticated
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Registered Successfully!!!");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) throws Exception {

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username);
        System.out.println(password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Login Successfully!!!");
        authResponse.setJwt(token);

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customerUserDetails.loadUserByUsername(username);

        System.out.println(userDetails);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
