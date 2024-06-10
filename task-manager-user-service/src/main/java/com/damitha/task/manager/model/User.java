package com.damitha.task.manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String password;
    private String email;
    private String role;
    private String fullName;
    private String mobile;


}
