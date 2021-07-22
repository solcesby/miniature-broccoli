package com.itechart.springproject.dto.user;

import com.itechart.springproject.dto.user.enums.UserRoleDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {

    private UUID id;
    private UserRoleDTO role;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
//    private String password;

}
