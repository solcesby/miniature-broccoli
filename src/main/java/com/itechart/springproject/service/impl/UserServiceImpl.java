package com.itechart.springproject.service.impl;

import com.itechart.springproject.dto.user.UserCreateDTO;
import com.itechart.springproject.dto.user.UserDTO;
import com.itechart.springproject.dto.user.UserUpdateDTO;
import com.itechart.springproject.entity.user.UserEntity;
import com.itechart.springproject.entity.user.UserRoleEntity;
import com.itechart.springproject.mapper.UserMapper;
import com.itechart.springproject.repository.UserRepository;
import com.itechart.springproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static com.itechart.springproject.entity.user.enums.RoleEntity.ROLE_USER;
import static java.lang.String.format;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO create(final UserCreateDTO userCreateDTO) {
        final UserEntity user = userMapper.toEntity(userCreateDTO);
        user.setRole(generateDefaultRole(user));
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setCreatedAt(now());

        throwIfUserExists(user);

        final UserEntity saved = userRepository.save(user);
        return userMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(final Integer page, final Integer size) {
        final Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> findAllActive(Integer page, Integer size) {
        final Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllByDeletedAtIsNull(pageable).map(userMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO get(final UUID id) {
        return userRepository.findById(id).map(userMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(format("User with id: %s not found", id)));
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(format("User with email: %s not found", email)));
    }

    @Override
    @Transactional
    public UserDTO update(final UserUpdateDTO userUpdateDTO) {
        final UserEntity user = userRepository.findById(userUpdateDTO.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(format("User with id: %s not found", userUpdateDTO.getId())));
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setPhone(userUpdateDTO.getPhone());
        user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        user.setUpdatedAt(now());

        final UserEntity updated = userRepository.save(user);
        return userMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(final UUID id) {
        final UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("User with id: %s not found", id)));

        user.setDeletedAt(now());
        final UserEntity updated = userRepository.save(user);
    }

    private void throwIfUserExists(final UserEntity user) {
        final String email = user.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new EntityExistsException(format("Email: %s already exists", email));
        }
    }

    private UserRoleEntity generateDefaultRole(UserEntity user) {
        final var role = new UserRoleEntity();
        role.setRole(ROLE_USER);
        role.setUser(user);
        return role;
    }
}
