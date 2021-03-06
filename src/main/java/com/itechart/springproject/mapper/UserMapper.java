package com.itechart.springproject.mapper;

import com.itechart.springproject.dto.user.UserCreateDTO;
import com.itechart.springproject.dto.user.UserDTO;
import com.itechart.springproject.entity.user.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserMapper USER_MAPPER = getMapper(UserMapper.class);

    UserDTO toDTO(UserEntity user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "recipientEmails", ignore = true)
    @Mapping(target = "recipients", ignore = true)
    UserEntity toEntity(UserCreateDTO userCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "recipients", ignore = true)
    @Mapping(target = "recipientEmails", ignore = true)
    UserEntity toEntity(UserDTO userDTO);

}
