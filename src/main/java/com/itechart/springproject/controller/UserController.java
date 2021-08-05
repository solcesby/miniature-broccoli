package com.itechart.springproject.controller;

import com.itechart.springproject.dto.user.UserCreateDTO;
import com.itechart.springproject.dto.user.UserDTO;
import com.itechart.springproject.dto.user.UserUpdateDTO;
import com.itechart.springproject.service.UserService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserControllerInfo {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(
            @ApiParam(value = "Required UserCreateDTO instance", required = true)
            @Valid @RequestBody final UserCreateDTO userCreateDTO) {
        final UserDTO user = userService.create(userCreateDTO);
        return new ResponseEntity<>(user, CREATED);
    }

    @GetMapping
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam(value = "page") final Integer page,
            @RequestParam(value = "size") final Integer size) {
        final Page<UserDTO> users = userService.findAll(page, size);
        return new ResponseEntity<>(users.getContent(), OK);
    }

    @GetMapping("/active")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<UserDTO>> getAllActiveUsers(
            @RequestParam(value = "page") final Integer page,
            @RequestParam(value = "size") final Integer size) {
        final Page<UserDTO> users = userService.findAllActive(page, size);
        return new ResponseEntity<>(users.getContent(), OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<UserDTO> getUserById(
            @ApiParam(value = "Id of user to lookup for", required = true)
            @PathVariable final UUID id) {
        final UserDTO user = userService.get(id);
        return new ResponseEntity<>(user, OK);
    }

    @PutMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<UserDTO> updateUser(
            @ApiParam(value = "Required UserUpdateDTO instance", required = true)
            @Valid @RequestBody final UserUpdateDTO userUpdateDTO) {
        final UserDTO user = userService.update(userUpdateDTO);
        return new ResponseEntity<>(user, OK);
    }

    @DeleteMapping(value = "/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> deleteUser(
            @PathVariable final UUID id) {
        userService.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
