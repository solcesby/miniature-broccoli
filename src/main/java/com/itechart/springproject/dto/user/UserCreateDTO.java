package com.itechart.springproject.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserCreateDTO {

    private String firstName;

    private String lastName;

    private String phone;

    @NotEmpty(message = "email can't be empty")
    private String email;

    @NotEmpty(message = "password can't be empty")
    private String password;

}
