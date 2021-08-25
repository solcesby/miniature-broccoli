package com.itechart.springproject.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserUpdateDTO {

    @NotNull(message = "id must be specified")
    private UUID id;

    private String firstName;

    private String lastName;

    private String phone;

    @NotEmpty(message = "email can't be empty")
    private String email;

    @NotEmpty(message = "password can't be empty")
    private String password;

}
