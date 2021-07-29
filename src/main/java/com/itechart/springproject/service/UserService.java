package com.itechart.springproject.service;

import com.itechart.springproject.dto.user.UserCreateDTO;
import com.itechart.springproject.dto.user.UserDTO;
import com.itechart.springproject.dto.user.UserUpdateDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserService {

    UserDTO create(UserCreateDTO userCreateDTO);

    Page<UserDTO> findAll(Integer page, Integer size);

    Page<UserDTO> findAllActive(Integer page, Integer size);

    UserDTO get(UUID id);

    UserDTO update(UserUpdateDTO userUpdateDTO);

    void delete(UUID id);
}
