package com.itechart.springproject.config;

import com.itechart.springproject.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UserMapper userMapper() {
        return UserMapper.USER_MAPPER;
    }

}
