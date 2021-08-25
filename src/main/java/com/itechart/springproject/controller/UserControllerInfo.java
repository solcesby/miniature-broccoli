package com.itechart.springproject.controller;

import com.itechart.springproject.dto.user.UserCreateDTO;
import com.itechart.springproject.dto.user.UserDTO;
import com.itechart.springproject.dto.user.UserUpdateDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserControllerInfo {

    @ApiOperation(authorizations = {@Authorization(value = "Authorization")},
            value = "Create new user", notes = "Required UserCreateDTO instance")
    UserDTO createUser(
            @ApiParam(value = "Required UserCreateDTO instance", required = true)
                    UserCreateDTO userCreateDTO);

    @ApiOperation(authorizations = {@Authorization(value = "Authorization")},
            value = "Return all users", notes = "This method will return all users")
    List<UserDTO> getAllUsers(
            @ApiParam(value = "Page number") Integer page,
            @ApiParam(value = "Page size") Integer size);

    @ApiOperation(authorizations = {@Authorization(value = "Authorization")},
            value = "Return all undeleted users", notes = "This method will return all undeleted users")
    List<UserDTO> getAllActiveUsers(
            @ApiParam(value = "Page number") Integer page,
            @ApiParam(value = "Page size") Integer size);

    @ApiOperation(authorizations = {@Authorization(value = "Authorization")},
            value = "Find user by id", notes = "Required user id")
    UserDTO getUserById(
            @ApiParam(value = "Id of user to lookup for", required = true)
                    UUID id);

    @ApiOperation(authorizations = {@Authorization(value = "Authorization")},
            value = "Update user", notes = "Required UserUpdateDTO instance")
    UserDTO updateUser(
            @ApiParam(value = "Required UserUpdateDTO instance", required = true)
                    UserUpdateDTO userUpdateDTO);

    @ApiOperation(authorizations = {@Authorization(value = "Authorization")},
            value = "Delete user by id", notes = "This method will delete user with specified id")
    void deleteUser(
            @ApiParam(value = "Id of a user to delete", required = true)
                    UUID id);
}
