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

    @ApiOperation(authorizations = {@Authorization(value = "All users can use this method")},
            value = "Create new user", notes = "Required UserCreateDTO instance")
    ResponseEntity<UserDTO> createUser(
            @ApiParam(value = "Required UserCreateDTO instance", required = true)
                    UserCreateDTO userCreateDTO);

    @ApiOperation(authorizations = {@Authorization(value = "Only admins can use this method")},
            value = "Return all users", notes = "This method will return all users")
    ResponseEntity<List<UserDTO>> getAllUsers(
            @ApiParam(value = "Page number") Integer page,
            @ApiParam(value = "Page size") Integer size);

    @ApiOperation(authorizations = {@Authorization(value = "All users can use this method")},
            value = "Return all undeleted users", notes = "This method will return all undeleted users")
    ResponseEntity<List<UserDTO>> getAllActiveUsers(
            @ApiParam(value = "Page number") Integer page,
            @ApiParam(value = "Page size") Integer size);

    @ApiOperation(authorizations = {@Authorization(value = "All users can use this method")},
            value = "Find user by id", notes = "Required user id")
    ResponseEntity<UserDTO> getUserById(
            @ApiParam(value = "Id of user to lookup for", required = true)
                    UUID id);

    @ApiOperation(authorizations = {@Authorization(value = "All users can use this method")},
            value = "Update user", notes = "Required UserUpdateDTO instance")
    ResponseEntity<UserDTO> updateUser(
            @ApiParam(value = "Required UserUpdateDTO instance", required = true)
                    UserUpdateDTO userUpdateDTO);

    @ApiOperation(authorizations = {@Authorization(value = "All users can use this method")},
            value = "Delete user by id", notes = "This method will delete user with specified id")
    ResponseEntity<Void> deleteUser(
            @ApiParam(value = "Id of a user to delete", required = true)
                    UUID id);
}
