package com.continental.blogapplication.usermanagement.dto;

import lombok.Data;

@Data
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String phone;
}
