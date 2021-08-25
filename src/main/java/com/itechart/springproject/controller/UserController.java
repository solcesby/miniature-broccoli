package com.itechart.springproject.controller;

import com.itechart.springproject.dto.user.UserCreateDTO;
import com.itechart.springproject.dto.user.UserDTO;
import com.itechart.springproject.dto.user.UserUpdateDTO;
import com.itechart.springproject.service.UserService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserControllerInfo {

    private final UserService userService;

    @PostMapping
    public UserDTO createUser(
            @ApiParam(value = "Required UserCreateDTO instance", required = true)
            @Valid @RequestBody final UserCreateDTO userCreateDTO) {
        return userService.create(userCreateDTO);
    }

    @GetMapping
    @RolesAllowed("ROLE_ADMIN")
    public List<UserDTO> getAllUsers(
            @RequestParam(value = "page") final Integer page,
            @RequestParam(value = "size") final Integer size) {
        final Page<UserDTO> users = userService.findAll(page, size);
        return users.getContent();
    }

    @GetMapping("/active")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public List<UserDTO> getAllActiveUsers(
            @RequestParam(value = "page") final Integer page,
            @RequestParam(value = "size") final Integer size) {
        final Page<UserDTO> users = userService.findAllActive(page, size);
        return users.getContent();
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public UserDTO getUserById(
            @ApiParam(value = "Id of user to lookup for", required = true)
            @PathVariable final UUID id) {
        return userService.get(id);
    }

    @PutMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public UserDTO updateUser(
            @ApiParam(value = "Required UserUpdateDTO instance", required = true)
            @Valid @RequestBody final UserUpdateDTO userUpdateDTO) {
        return userService.update(userUpdateDTO);
    }

    @DeleteMapping(value = "/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public void deleteUser(
            @PathVariable final UUID id) {
        userService.delete(id);
    }
}
