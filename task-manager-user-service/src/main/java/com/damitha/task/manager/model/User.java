package com.damitha.task.manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_entity", schema = "public")
public class User implements Serializable {

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "email_constraint", length = 255)
    private String emailConstraint;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified = false;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = false;

    @Column(name = "federation_link", length = 255)
    private String federationLink;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "realm_id", length = 255)
    private String realmId;

    @Column(name = "username", length = 255)
    private String username;

    @Column(name = "created_timestamp")
    private Long createdTimestamp;

    @Column(name = "service_account_client_link", length = 255)
    private String serviceAccountClientLink;

    @Column(name = "not_before", nullable = false)
    private int notBefore = 0;

    @Column(name = "profile_picture")
    private String profilePicture;


}


//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name="Users")
//public class User{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer userId;
//
//    private String userName;
//    private String firstName;
//    private String lastName;
//    private String password;
//    private String email;
//    private String role;
//    private String mobile;
//
//
//}
