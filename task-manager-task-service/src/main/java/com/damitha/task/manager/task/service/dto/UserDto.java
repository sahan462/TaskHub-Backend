package com.damitha.task.manager.task.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer userId;

    private String password;
    private String email;
    private String role;
    private String fullName;
    private String mobile;
}
