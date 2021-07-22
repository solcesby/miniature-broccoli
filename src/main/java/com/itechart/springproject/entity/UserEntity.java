package com.itechart.springproject.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserEntity {

    private UUID id;

    private UserRoleEntity role;

    private String firstName;

    private String lastName;

    //TODO
    // phone:
    //      type: string
    //      format: phone
    //      example: "+753 92 321 54 76"
    // not sure if the type should be Phone instead of String
    private String phone;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

}
