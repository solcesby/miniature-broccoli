package com.itechart.springproject.service.impl;

import com.itechart.springproject.entity.user.UserEntity;
import com.itechart.springproject.repository.UserRepository;
import com.itechart.springproject.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        return user.map(Principal::new)
                .orElseThrow(() -> new UsernameNotFoundException(format("User with email %s not found", email)));
    }
}
